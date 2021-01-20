package com.onemore.goodproduct.acitivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.gw.library.Logger;
import com.onemore.goodproduct.R;
import com.onemore.goodproduct.util.CommonUtil;
import com.onemore.goodproduct.view.TitleBarView;

import butterknife.ButterKnife;


/**
 * state：基类，封装了很多的方法，方便activity里面使用重复的代码，多余
 * date:2018/8/26
 * code:https://github.com/tangpeng
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean isSetStatusBar = true;//是否沉浸状态栏
    private boolean mAllowFullScreen = false;//是否允许全屏
    private boolean isAllowScreenRoate = false;//是否禁止旋转屏幕
    protected final String TAG = "BASEACTIVITY";//日志输出标志

    public TitleBarView mTitleBarView;

    public Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.i(TAG, "BaseActivity()+onCreate");
        Bundle bundle = getIntent().getExtras();
        initParms(bundle);
        super.onCreate(savedInstanceState);
        mContext = this;
        //使用 AppCompatActivity需要把requestWindowFeature放在super.onCreate(savedInstanceState)前面,而activity不需要

//        IntentFilter intentFilter = new IntentFilter();  //不需要在全局获取是否有网络，在接口请求的时候，判断了
//        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(mNetworkReceiver, intentFilter);

//        StatusBarUtilLocal.transparencyBar(BaseActivity.this);
//        StatusBarUtilLocal.StatusBarLightMode(BaseActivity.this);

        if (getChildView() != 0) {
            setContentView(getChildView());
            ButterKnife.bind(this);
        }
        BaseInit();
        initData(this);
        setListener(this);
        doBusiness(this);

    }

    /**
     * 返回本界面的布局文件
     */
    public abstract int getChildView();
    /**
     * 初始化
     */
    public void BaseInit() {
        mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);

    }


    /**
     *
     */
    public abstract void initData(Context mContext);


    /**
     * [初始化参数]
     *
     * @param parms
     */
    public abstract void initParms(Bundle parms);


    /**
     * [设置监听]
     */
    public abstract void setListener(Context mContext);

    /**
     * View点击
     **/
    public abstract void widgetClick(View v);

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    /**
     * [业务操作]
     *
     * @param mContext
     */
    public abstract void doBusiness(Context mContext);

    /**
     * [页面跳转]
     *
     * @param clz
     * @param isfinish 是否需要销毁当前activity
     */
    public void startActivity(Class<?> clz, boolean isfinish) {
        startActivity(new Intent(BaseActivity.this, clz));
        if (isfinish) {
            finish();
        }
    }

    /**
     * 状态栏是否填充
     *
     * @param isFullStatusBar
     */
    public void setFullStatusBar(boolean isFullStatusBar) {
        if (isFullStatusBar) {
            mTitleBarView.getTitleTop().setFitsSystemWindows(false);
        } else {
            mTitleBarView.getTitleTop().setFitsSystemWindows(true);
        }
    }


    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            CommonUtil.HideKeyboard(mTitleBarView);
            this.finish();
            Logger.i(TAG, "onKeyDown");
        }
        return false;
    }

    public void clickRetrun(View v) {
        Logger.i(TAG, "clickRetrun");
        CommonUtil.HideKeyboard(v);
        finish();
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.d(TAG, "BaseActivity()+onDestroy()");

    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * [是否允许全屏]
     *
     * @param allowFullScreen
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    /**
     * [是否设置沉浸状态栏]
     *
     * @param isSetStatusBar
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * [是否允许屏幕旋转]
     *
     * @param isAllowScreenRoate
     */
    public void setScreenRoate(boolean isAllowScreenRoate) {
        this.isAllowScreenRoate = isAllowScreenRoate;
    }

    /**
     * [沉浸状态栏]
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public void baseFinnish() {
        finish();
    }

}