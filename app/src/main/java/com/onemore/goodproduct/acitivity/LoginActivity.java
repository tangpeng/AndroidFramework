package com.onemore.goodproduct.acitivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.onemore.goodproduct.R;
import com.onemore.goodproduct.mvpview.MvpUserActivityView;
import com.onemore.goodproduct.presenter.impl.UserPresenter;
import com.onemore.goodproduct.util.Tools;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * state：登陆页面 深圳好产品
 * date:2018/8/26
 * code:https://github.com/tangpeng
 */
public class LoginActivity extends BaseActivity implements MvpUserActivityView {
    private Context context = LoginActivity.this;

    @BindView(R.id.login_progress)
    ProgressBar loginProgress;
    @BindView(R.id.email)
    AutoCompleteTextView email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.email_sign_in_button)
    Button emailSignInButton;
    @BindView(R.id.email_login_form)
    LinearLayout emailLoginForm;
    @BindView(R.id.login_form)
    ScrollView loginForm;

    //mpv的框架,
    UserPresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    public int getChildView() {
        return R.layout.activity_login;
    }

    public void initDataMvp() {
        presenter = new UserPresenter(this);
        presenter.attach(this);
    }

    @Override
    public void initData(Context mContext) {
        initDataMvp();
        setFullStatusBar(true);
    }

    @Override
    public void initParms(Bundle parms) {
    }

    @Override
    public void setListener(Context mContext) {
        emailSignInButton.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        presenter.userLogin(context, 1, email.getText().toString(), password.getText().toString());
    }

    @Override
    public void doBusiness(Context mContext) {
    }



    @Override
    public void MVPFail(String data) {

    }

    @Override
    public void MVPSuccess(Object data) {
        Tools.showToast(context, "data=" + data);
        finish();
    }
}

