package com.onemore.goodproduct.presenter.impl;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import com.onemore.goodproduct.constant.ComParamContact;
import com.onemore.goodproduct.model.AuthModel;
import com.onemore.goodproduct.mvpview.MvpUserActivityView;
import com.onemore.goodproduct.presenter.BasePresenter;
import com.onemore.goodproduct.util.MD5;
import com.onemore.goodproduct.util.Tools;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.ProgressDialogCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.subsciber.IProgressDialog;

/**
 * MVP,对于逻辑处理模块，应该分离出来，以及需要多次多次使用的方法，提取出来，像登陆注册使用mvp开发，就会发现MVP的高超之处
 * https://segmentfault.com/a/1190000003927200
 */
public class UserPresenter extends BasePresenter {
    private static String TAG = "USERPRESENTER";
    MvpUserActivityView activityView;
    private int userId = 0;
    private String userNick = "";

    public UserPresenter(MvpUserActivityView activityView) {
        this.activityView = activityView;

    }

    @Override
    public void attach(Context context) {
        super.attach(context);
    }

    /**
     * 用户注册
     * 这里编写异步请求服务器
     */
    public void userRegister(final Context context, String code, String country_code, final String mobile, final String password) {

    }

    /**
     * 发送验证码
     * 这里编写异步请求服务器
     */
    public void sendsms(final Context context, int s_type, String country_code, String accepter) {

    }

    /**
     * 用户登录
     */
    public void userLogin(final Context context, int login_type, final String mobile, String password) {
        IProgressDialog mProgressDialog = new IProgressDialog() {
            @Override
            public Dialog getDialog() {
                ProgressDialog dialog = new ProgressDialog(context);
                dialog.setMessage("登录中...");
                return dialog;
            }
        };
        EasyHttp.post(ComParamContact.Login.PATH)
                .params(ComParamContact.Login.ACCOUNT, mobile)
                .params(ComParamContact.Login.PASSWORD, MD5.toMD5(password))
                .sign(true)
                .timeStamp(true)
                .execute(new ProgressDialogCallBack<AuthModel>(mProgressDialog, true, true) {
                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        Tools.showToast(context, "登录失败！=" + e.getMessage());
                    }

                    @Override
                    public void onSuccess(AuthModel authModel) {
                        Tools.showToast(context, "登录成功！");
                        //授权类信息存入缓存
                        getLoginSuccessInfo(context, 1, authModel, mobile);
                    }
                });

    }

    /**
     * 登录成功，统一处理  三个地方（登录页面，第三方登录，绑定手机号码）
     *
     * @param mobile
     */
    public void getLoginSuccessInfo(Context context, int type, AuthModel authModel, String mobile) {

        activityView.MVPSuccess(authModel.getAccessToken());
    }

    /**
     * 第三方登录授权，从账户安全进入
     */
    public void userThirdGrant(final Context context, final int login_type, final String callback) {

    }

    /**
     * 第三方登录授权
     */
    public void userThirdGrantPreson(final Context context, final int login_type, final String callback) {

    }


    /**
     * 第三方登录绑定手机号码
     */
    public void userThirdGrantPhone(final Context context, int login_type, String callback, final String mobile, String code, String country_code) {


    }

    ;
}
