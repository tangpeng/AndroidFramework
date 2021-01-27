package com.onemore.goodproduct.acitivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.gw.library.ARouterActivity;
import com.gw.library.Module;
import com.onemore.goodproduct.R;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * des: https://www.jianshu.com/p/e095f5cafc7e
 * 基于Android Okhttp3 webSocket 错误重连和保证长连接(两个线程交替进行)
 * https://blog.csdn.net/InnovationAD/article/details/80049515
 * author:lucas tangpeng
 * date:2020-05-20 14:59
 */
@Route(path = Module.MODULE_TWO)
public class WebSocketActivity extends AppCompatActivity {
    public static final int LOOP = 2; // 处理消息循环
    public static final int SEND = 3; // 发送消息

    @BindView(R.id.btn)
    Button btn;

    private long sendTime = 0L;
    private final static String heartAction = "{\"action\":\"ws_heartbeat_resp\"}";
    String json = "{\"action\":\"ws_sub_ackline_req\",\"symbols\":[\"BTCUSDT\",\"ETHUSDT\",\"LTCUSDT\"]}";

    //Handler造成内存泄露的原因。非静态内部类，或者匿名内部类。使得Handler默认持有外部类的引用。
    //在Activity销毁时，由于Handler可能有**未执行完/正在执行的Message**。导致Handler持有Activity的引用。
    //进而导致GC无法回收Activity。
    //解决办法:  (1)Activity销毁时，清空Handler中，未执行或正在执行的Callback以及Message。 (2)静态内部类+弱引用

//    //匿名内部类
//    Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//        }
//    };

    //非静态内部类
//    protected class AppHandler extends Handler {
//
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                // TODO: 2019/4/30
//            }
//        }
//    }

    // 发送心跳包 匿名内部类
    private Handler mHandler = new Handler();
    // 每隔2秒发送一次心跳包，检测连接没有断开
    private static final long HEART_BEAT_RATE = 6000;


    OkHttpClient mHttpClient = null;
    Request mRequset = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        btn.setText("范德萨发了上课的");

        mHttpClient = new OkHttpClient.Builder()
                .readTimeout(2, TimeUnit.SECONDS)
                .writeTimeout(2, TimeUnit.SECONDS)
                .connectTimeout(2, TimeUnit.SECONDS)
                .build();

        mRequset = new Request.Builder().url("ws://ixpe4.ecproxy4.com:8800/ws").build();

        initData();
        String name = getIntent().getStringExtra("name")+"";
        if (!TextUtils.isEmpty(name)) {
            Toast.makeText(WebSocketActivity.this, name, Toast.LENGTH_LONG).show();
        }
    }

    // 发送心跳包
    private Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            if (System.currentTimeMillis() - sendTime >= HEART_BEAT_RATE) {
                if (webSocketInit != null) {
                    // Gecko 返回一个boolean 来告知当前连接是否依旧处于OPEN 状态 (同时也可以使用该返回值来判定数据是否已经被完全缓存或者传输);
                    webSocketInit.send(heartAction);
                    Log.i("lucas", "send");
                }
                sendTime = System.currentTimeMillis();
            }
            mHandler.postDelayed(this, HEART_BEAT_RATE); //每隔一定的时间，对长连接进行一次心跳检测
        }
    };

    private void initData() {


        mHttpClient.newWebSocket(mRequset, new EchoWebSocketListener());

        // 刚进入界面，就开启心跳检测
//        从当前时间开始延迟delayMillis时间后执行Runnable，只执行一次。

        mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);

        mHttpClient.dispatcher().executorService().shutdown();//清除并关闭线程池
    }

    private WebSocket webSocketInit;

    public void onClickARouter(View view) {
        ARouter.getInstance().build(Module.MODULE_ONE).withString("name", "lucas").navigation();
    }

    class EchoWebSocketListener extends WebSocketListener {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            Log.i("lucas", "onOpen=" + response.toString());
            super.onOpen(webSocket, response);
            webSocketInit = webSocket;
            if (!TextUtils.isEmpty(json)) {
                webSocketInit.send(json);
            }
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            Log.i("lucas", "text=" + text);
            //{"action":"ws_heartbeat_req","server_time":1590048796}
            // //现在btcc这边的做法是,保存server_time的时间,判断是如果消息20秒没有数据返回就要重新连接
            output("onMessage" + text);

        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
            output("closed:" + reason);
            Log.i("lucas", "onClosed");
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
            output("closing:" + reason);
            Log.i("lucas", "onClosing");
        }

        //如果通信过程中出现错误或者连接断开会回掉在onFailure方法中
        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
            t.printStackTrace();

            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
            initData();
//            Log.i("lucas", response.toString()+"-onFailure-"+t.toString());
        }
    }

    private void output(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btn.setText(text);

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        if (webSocketInit != null) {
            webSocketInit.close(1000, null);
        }
    }
}
