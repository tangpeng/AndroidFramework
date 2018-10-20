package com.onemore.goodproduct;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.bumptech.glide.request.target.ViewTarget;
import com.onemore.goodproduct.constant.AppConstant;
import com.onemore.goodproduct.util.SystemInfoUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.SerializableDiskConverter;
import com.zhouyou.http.model.HttpHeaders;
import com.zhouyou.http.model.HttpParams;
import com.zhouyou.http.utils.HttpLog;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;


/**
 * state：深圳好产品
 * date:2018/8/26
 * code:https://github.com/tangpeng
 */
public class App extends MultiDexApplication {
    private static final String TAG = "APP";
    private List<Activity> list = new ArrayList<Activity>();
    private static App ea;
    public static Context applicationContext;
    private static App instance;

    public App() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        ea = this;
        applicationContext = this;
        instance = this;

        /**
         * 当出现异常，退出app
         *  * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.
         */
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());

        //这里涉及到安全我把url去掉了，demo都是调试通的
        String Url = "http://www.xxx.com";
        EasyHttp.init(this);

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.put("User-Agent", SystemInfoUtils.getUserAgent(this, AppConstant.APPID));
        //设置请求参数
        HttpParams params = new HttpParams();
        params.put("appId", AppConstant.APPID);
        EasyHttp.getInstance()
                .debug("RxEasyHttp", true)
                .setReadTimeOut(60 * 1000)
                .setWriteTimeOut(60 * 1000)
                .setConnectTimeout(60 * 1000)
                .setRetryCount(3)//默认网络不好自动重试3次
                .setRetryDelay(500)//每次延时500ms重试
                .setRetryIncreaseDelay(500)//每次延时叠加500ms
                .setBaseUrl(Url)
                .setCacheDiskConverter(new SerializableDiskConverter())//默认缓存使用序列化转化
                .setCacheMaxSize(50 * 1024 * 1024)//设置缓存大小为50M
                .setCacheVersion(1)//缓存版本为1
                .setHostnameVerifier(new UnSafeHostnameVerifier(Url))//全局访问规则
                .setCertificates()//信任所有证书
                //.addConverterFactory(GsonConverterFactory.create(gson))//本框架没有采用Retrofit的Gson转化，所以不用配置
                .addCommonHeaders(headers)//设置全局公共头
                .addCommonParams(params);//设置全局公共参数
//                .addInterceptor(new CustomSignInterceptor());//添加参数签名拦截器
        //.addInterceptor(new HeTInterceptor());//处理自己业务的拦截器

        ViewTarget.setTagId(R.id.glide_tag);//glide和TAG有冲突
    }

    public static App getInstance() {
        if (null == ea) {
            ea = new App();
        }
        return ea;
    }

    /**
     * 添加acitivity到集合
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        list.add(activity);
    }

    /**
     * 退出app，关闭所有的activity
     *
     * @param context
     */
    public void exit(Context context) {
        for (Activity activity : list) {
            activity.finish();
        }
        System.exit(0);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public class UnSafeHostnameVerifier implements HostnameVerifier {
        private String host;

        public UnSafeHostnameVerifier(String host) {
            this.host = host;
            HttpLog.i("###############　UnSafeHostnameVerifier " + host);
        }

        @Override
        public boolean verify(String hostname, SSLSession session) {
            HttpLog.i("############### verify " + hostname + " " + this.host);
            if (this.host == null || "".equals(this.host) || !this.host.contains(hostname))
                return false;
            return true;
        }
    }

}