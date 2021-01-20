package com.onemore.goodproduct.acitivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.gw.library.Logger;
import com.onemore.goodproduct.R;
import com.onemore.goodproduct.util.AppContances;
import com.onemore.goodproduct.view.BtnClickListener;
import com.onemore.goodproduct.view.CommonTitleBar2;
import com.onemore.goodproduct.view.CommonUtils;
import com.onemore.goodproduct.view.TitleBarView;
import com.onemore.goodproduct.view.WebProgress;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;


public class NewsDetailActivity extends BaseActivity {
    public static String TAG = "NewsDetailActivity";
    public Activity context = NewsDetailActivity.this;
    public Timer timer = new Timer();
    public TimerTask task;

    @BindView(R.id.webview)
    public WebView mWebView;
    @BindView(R.id.webProgressbar)
    WebProgress webProgressbar;

    @BindView(R.id.error_layout)
    View mErrorView;
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                switch (msg.what) {
                    case AppContances.HANDLER_TIME_OUT:
//                            showToastPopWindow(ConfigUtil.instance().getErrorConfigObject().optString(ConfigType.SERVER_ERROR_1012));
                        cancelTask();
                        if(mWebView!=null){
                            mWebView.setVisibility(View.GONE);
                            mErrorView.setVisibility(View.VISIBLE);
                        }
                        break;
                }
            } catch (Exception e) {
                Logger.e("加载网页异常！", e);
            }

        }
    };
    @BindView(R.id.common_title_bar)
    TitleBarView mTitleBar;
    @BindView(R.id.net_error_btn)
    TextView netErrorBtn;


    private String mTitle;
    private String mNewsUrl = "";
    private String mSubType = "";
    private String mId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 在 super.onCreate(savedInstanceState) 之前调用该方法
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getChildView() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initData(Context mContext) {
        mTitle = getIntent().getStringExtra("title");
        mNewsUrl = getIntent().getStringExtra("url");
        mSubType = getIntent().getStringExtra("subType");
        mTitleBar.setTitleText(mTitle);
//        mTitleBar.setRightResource(R.mipmap.a_icon_menu_share, 0);

        webProgressbar.show();
        webProgressbar.setProgress(10);
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void setListener(Context mContext) {
        mTitleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.ll_title_left_back) {
                    finish();
                }
            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        mWebView.setVisibility(View.GONE);

        /* 设置支持Js,必须设置的,基本大多数网页都涉及js */
        mWebView.getSettings().setJavaScriptEnabled(true);
        /* 设置为true时表示支持使用js打开新的窗口 */
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        /* 当网页需要保存数时据,设置下面属性 */
        mWebView.getSettings().setDomStorageEnabled(true);
        /* 设置为使用webview推荐的窗口，主要是为了配合下一个属性 */
        mWebView.getSettings().setUseWideViewPort(true);
        /* 设置网页自适应屏幕大小，该属性必须和上一属性配合使用 */
        mWebView.getSettings().setLoadWithOverviewMode(true);
        /* 启用还H5的地理定位服务 */
        mWebView.getSettings().setGeolocationEnabled(true);
        /* 设置是否允许webview使用缩放的功能 */
        mWebView.getSettings().setBuiltInZoomControls(false);
        /* 提高网页渲染的优先级 */
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        /* 设置是否显示水平滚动条 */
        mWebView.setHorizontalScrollBarEnabled(false);
        /* 设置垂直滚动条是否有叠加样式 */
        mWebView.setVerticalScrollbarOverlay(true);
        /* 设置滚动条的样式 */
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        mWebView.getSettings().setDefaultTextEncodingName("utf-8");

//        if (ConfigUtil.instance().isMarketOppoInfo()){
//            //不使用加速
//            mWebView.setLayerType(View.LAYER_TYPE_NONE, null);
//        }else{
//            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);//开启硬件加速
//        }

        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// 设置允许加载http的图片，默认https的地址仅允许加载https的图片
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mWebView.addJavascriptInterface(new JSObject(), "isNative");

        mWebView.setWebViewClient(new MyWebViewClient());

//        mWebView.addJavascriptInterface(this, "App");

        mWebView.setWebChromeClient(new ReWebChomeClients());

        if (mWebView != null) {

            initTimerTask();
            mWebView.loadUrl(mNewsUrl);
//            mWebView.loadUrl("https://xw.qq.com");
        }

    }

    /*
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {


    }


    /**
     * 实现文本复制功能
     *
     * @param content
     */
    public void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }


    private class ReWebChomeClients extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            Logger.i(TAG, "newProgress=" + newProgress);
            if (newProgress >= 100) {
                cancelTask();
            }
            setProgressbar(newProgress);
            super.onProgressChanged(view, newProgress);
        }

    }


    private void setProgressbar(int newProgress) {
        if (webProgressbar != null) {
            webProgressbar.setWebProgress(newProgress);
        }
    }

    /**
     * 初始化定时任务(处理提交订单超时)
     */
    public void initTimerTask() {
        try {
            // 初始化定时器
            task = new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(AppContances.HANDLER_TIME_OUT);
                }
            };
            // 启动定时器,设定时间内如果没有响应,则提示超时信息
            timer.schedule(task, AppContances.WEBVIEW_TIME_OUT);
        } catch (Exception e) {
            Logger.e("初始化定时任务异常！", e);
        }
    }

    /**
     * 取消超时定时
     */
    public void cancelTask() {
        if (null != task) {
            task.cancel();
            task = null;
        }
    }

    @OnClick(R.id.net_error_btn)
    public void errorClick(View view) {
        if (CommonUtils.isFastDoubleClick()) {
            return;
        }
        if (null != mErrorView) {
            mErrorView.setVisibility(View.GONE);
        }
        if (null != mWebView) {
            initTimerTask();
            mWebView.loadUrl(mNewsUrl);
//            mWebView.loadUrl("https://xw.qq.com");
        }
    }


    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        cancelTask();
        if (null != timer) {
            timer.cancel();
            timer = null;
        }

    }

    /**
     * 先执行onDetachedFromWindow()，后执行WebView.destroy()解决webview内存泄露的问题
     **/
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        destroy();
    }

    public void destroy() {
        if (mWebView != null) {
            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }

            mWebView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();

            try {
                mWebView.destroy();
            } catch (Throwable ex) {

            }
        }
    }

    class MyWebViewClient extends WebViewClient {
        //重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开。
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            Logger.e("加载网页成功..shouldOverrideUrlLoading");
            mWebView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            cancelTask();
            if (mErrorView.getVisibility() != View.VISIBLE) {
                mWebView.setVisibility(View.VISIBLE);
                mErrorView.setVisibility(View.GONE);
//            Logger.e("加载网页失败..onPageFinished");

                if (webProgressbar != null) {
                    webProgressbar.hide();
                }
            }
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            cancelTask();
            mErrorView.setVisibility(View.VISIBLE);
            webProgressbar.setWebProgress(100);
            mWebView.setVisibility(View.GONE);

            if (webProgressbar != null) {
                webProgressbar.hide();
            }

            super.onReceivedError(view, request, error);
            Logger.e("onReceivedError..error=" + error.toString());
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            Logger.e("onReceivedSslError..error=" + error.toString());
            handler.proceed();
            //用WebView访问证书有问题的SSL网页  暂时过滤

        }
    }

    public class JSObject {

        @JavascriptInterface
        public void toAppPage(String types) {
//            new WebPresenter().JsByApp(gw.com.android.ui.news.NewsDetailActivity.this, null, mWebView, types);
        }
    }
}


