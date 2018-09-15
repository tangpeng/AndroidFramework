package com.onemore.goodproduct.acitivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.onemore.goodproduct.R;
import com.onemore.goodproduct.util.CleanMessageUtil;
import com.onemore.goodproduct.util.MyLog;
import com.onemore.goodproduct.util.Tools;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


/**
 * state：深圳好产品
 * date:2018/9/11
 * code:https://github.com/tangpeng
 */
public class SetActivity extends BaseActivity {
    private static final String TAG = "SETACTIVITY";
    @BindView(R.id.switchTrack)
    SwitchCompat switchTrack;
    @BindView(R.id.tvUserCache)
    TextView tvUserCache;
    @BindView(R.id.llUserCache)
    LinearLayout llUserCache;
    @BindView(R.id.llUserLogout)
    LinearLayout llUserLogout;
    private Context context = SetActivity.this;

    RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getChildView() {
        return R.layout.activity_set;
    }

    @Override
    public void initData(Context mContext) {
        MyLog.i(TAG, "initData");
        rxPermissions = new RxPermissions(this);
        try {
            tvUserCache.setText(CleanMessageUtil.getTotalCacheSize(context) + "");
        } catch (Exception e) {
            e.printStackTrace();
            tvUserCache.setText("");
        }
    }


    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void setListener(Context mContext) {

        llUserCache.setOnClickListener(this);
        llUserLogout.setOnClickListener(this);
        switchTrack.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.llUserCache:
                rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(@NonNull Boolean aBoolean) throws Exception {
                                if(aBoolean){
                                    Tools.showToast(context, getString(R.string.success_cancel_cache));
                                    // 必须在UI线程中调用
                                    Glide.get(context).clearMemory();
                                    //必须在子线程中调用，建议同时clearMemory()
                                    clearDiskCache();

                                }else {
                                    Toast.makeText(context, "权限获取失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                break;
            case R.id.llUserLogout:

                showLogoutDialogs(context, 1);
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    private void showLogoutDialogs(final Context context, int toastType) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        if (!dialog.isShowing()) {
            dialog.show();
        }
        View localView = LayoutInflater.from(context).inflate(
                R.layout.dialog_logout, null);

        dialog.setContentView(localView);

        TextView btnExitLoginCancel = (TextView) localView.findViewById(R.id.btnExitLoginCancel);
        btnExitLoginCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        });
        TextView btnExitLoginSure = (TextView) localView.findViewById(R.id.btnExitLoginSure);
        btnExitLoginSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }

                startActivity(LoginActivity.class, true);
            }

        });
        ImageView ivExitLogin = (ImageView) localView.findViewById(R.id.ivExitLogin);
        TextView tv_update_app_context = (TextView) localView.findViewById(R.id.tv_update_app_context);
        if (toastType == 1) {
            ivExitLogin.setImageResource(R.drawable.ic_my_quit_nor);
            tv_update_app_context.setText(context.getString(R.string.exit_id));
        }
    }

    /**
     * 清除磁盘缓存.
     */
    private void clearDiskCache() {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                // This method must be called on a background thread.
                Glide.get(getApplicationContext()).clearDiskCache();
                return null;
            }
        };
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }
}

