package com.onemore.goodproduct.acitivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apeng.permissions.EsayPermissions;
import com.apeng.permissions.OnPermission;
import com.apeng.permissions.Permission;
import com.gw.library.Logger;
import com.onemore.goodproduct.R;
import com.onemore.goodproduct.fragment.FragmentFind;
import com.onemore.goodproduct.fragment.FragmentIndex;
import com.onemore.goodproduct.fragment.FragmentMe;
import com.onemore.goodproduct.util.Constans;
import com.onemore.goodproduct.util.Tools;
import com.onemore.goodproduct.view.TitleBarView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * state：深圳好产品
 * date:2018/9/11
 * code:https://github.com/tangpeng
 */
public class MainActivity extends BaseActivity {
    private final String TAG = "MainActivity";
    @BindView(R.id.title_bar)
    TitleBarView titleBar;
    @BindView(R.id.content)
    FrameLayout content;

    @BindView(R.id.ivMainIndex)
    ImageView mivMainIndex;
    @BindView(R.id.tvMainIndex)
    TextView mtvMainIndex;
    @BindView(R.id.rlMainIndex)
    RelativeLayout mrlMainIndex;

    @BindView(R.id.ivMainDirector)
    ImageView mivMainDirector;
    @BindView(R.id.tvMainDirector)
    TextView tvMainDirector;
    @BindView(R.id.rlMainDirector)
    RelativeLayout mrlMainDirector;

    @BindView(R.id.ivMainMe)
    ImageView ivMainMe;
    @BindView(R.id.tvMainMe)
    TextView tvMainMe;
    @BindView(R.id.rlMainMe)
    RelativeLayout mrlMainMe;

    private Context context = MainActivity.this;


    FragmentManager fManager;
    private FragmentIndex mFragmentIndex; //0
    private FragmentFind mFragmentFind;//1
    private FragmentMe mFragmentMe;//2


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public int getChildView() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Context mContext) {
        fManager = getSupportFragmentManager();
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void setListener(Context mContext) {
        setFullStatusBar(true);

        mrlMainIndex.setOnClickListener(this);
        mrlMainDirector.setOnClickListener(this);
        mrlMainMe.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.rlMainIndex://
                setChioceItem(0);
                break;
            case R.id.rlMainDirector://
                setChioceItem(1);
                break;
            case R.id.rlMainMe:
                setChioceItem(2);
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        selectFragment(0);
        getPermissions();
    }


    /**
     * 选择跳转到某个fragment
     */
    void selectFragment(int returnType) {
        Logger.i(TAG, "returnType=" + returnType);
        if (returnType == 2) {
            setChioceItem(2);
        } else if (returnType == 1) {
            setChioceItem(1);
        } else {
            setChioceItem(0);
        }
    }


    // 定义一个选中一个item后的处理
    public void setChioceItem(int index) {

        // 重置选项+隐藏所有Fragment
        FragmentTransaction transaction = fManager.beginTransaction();
        clearChioce();
        hideFragments(transaction);
        switch (index) {
            case 0://首页
                mivMainIndex.setImageResource(R.drawable.ic_nav_sy_pressed);
                mtvMainIndex.setTextColor(getResources().getColor(R.color.index_color));
                if (mFragmentIndex == null) {
                    mFragmentIndex = new FragmentIndex();
                    transaction.add(R.id.content, mFragmentIndex);
                    Logger.i(TAG, "new FragmentIndex()");
                } else {
                    Logger.i(TAG, "transaction.show(mFragmentIndex);");
                    transaction.show(mFragmentIndex);
                }

                break;
            case 1://
                mivMainDirector.setImageResource(R.drawable.ic_home_jztg_pressed);
                tvMainDirector.setTextColor(getResources().getColor(R.color.index_color));
                if (mFragmentFind == null) {
                    mFragmentFind = new FragmentFind();
                    transaction.add(R.id.content, mFragmentFind);
                } else {
                    transaction.show(mFragmentFind);
                }
                Logger.i(TAG, "剧组通告");
                break;
            case 2:
                ivMainMe.setImageResource(R.drawable.ic_nav_wd_pressed);
                tvMainMe.setTextColor(getResources().getColor(R.color.index_color));
                if (mFragmentMe == null) {
                    mFragmentMe = new FragmentMe();
                    transaction.add(R.id.content, mFragmentMe);
                } else {
                    transaction.show(mFragmentMe);
                }
                Logger.i(TAG, "我的");
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏所有的Fragment,避免fragment混乱
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mFragmentIndex != null) {
            transaction.hide(mFragmentIndex);
        }
        if (mFragmentFind != null) {
            transaction.hide(mFragmentFind);
        }
        if (mFragmentMe != null) {
            transaction.hide(mFragmentMe);
        }
    }

    /**
     * 定义一个重置所有选项的方法
     */
    public void clearChioce() {

        mivMainIndex.setImageResource(R.drawable.ic_nav_sy_nor);
        mtvMainIndex.setTextColor(getResources().getColor(
                R.color.common_text_color));

        mivMainDirector.setImageResource(R.drawable.ic_home_jztg_nor);
        tvMainDirector.setTextColor(getResources().getColor(
                R.color.common_text_color));

        ivMainMe.setImageResource(R.drawable.ic_nav_wd_nor);
        tvMainMe.setTextColor(getResources().getColor(
                R.color.common_text_color));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constans.RESULT_OK) {
            Logger.i(TAG, "mian");
            fManager = getSupportFragmentManager();
            switch (requestCode) {
                case Constans.FRAGINDEX:
                    mFragmentIndex = (FragmentIndex) fManager.findFragmentByTag("mFragmentIndex");
                    // /*然后在碎片中调用重写的onActivityResult方法*/
                    mFragmentIndex.onActivityResult(requestCode, resultCode, data);
                    break;
                case Constans.FRAGDIRECTOR:
                    mFragmentFind = (FragmentFind) fManager.findFragmentByTag("mFragmentDirector");
                    // /*然后在碎片中调用重写的onActivityResult方法*/
                    mFragmentFind.onActivityResult(requestCode, resultCode, data);
                    break;
                case Constans.FRAGME:
                    mFragmentMe = (FragmentMe) fManager.findFragmentByTag("mFragmentMe");
                    // /*然后在碎片中调用重写的onActivityResult方法*/
                    mFragmentMe.onActivityResult(requestCode, resultCode, data);
                    break;
                default:

                    break;
            }
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Logger.i(TAG, "onNewIntent");
        selectFragment(intent.getIntExtra("Fragment", 0));
    }

    public void getPermissions() {

        EsayPermissions.with(this)
                .constantRequest() //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
//                .permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES) //支持请求6.0悬浮窗权限8.0请求安装权限
                .permission(Permission.WRITE_EXTERNAL_STORAGE)
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            Tools.showToast(context, "权限获取成功");
                        } else {
                            Tools.showToast(context, "获取权限成功，部分权限未正常授予");
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (quick) {
                            Tools.showToast(context, "被永久拒绝授权，请手动授予权限");
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            EsayPermissions.gotoPermissionSettings(context);
                        } else {
                            Tools.showToast(context, "获取权限失败");
                        }
                    }
                });
    }


}
