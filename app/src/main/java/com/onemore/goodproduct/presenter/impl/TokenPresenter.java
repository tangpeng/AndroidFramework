package com.onemore.goodproduct.presenter.impl;

import android.content.Context;


import com.onemore.goodproduct.mvpview.MvpGetTokenView;
import com.onemore.goodproduct.presenter.BasePresenter;
import com.onemore.goodproduct.util.MyLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * MVP,对于逻辑处理模块，应该分离出来，以及需要多次多次使用的方法，提取出来，像登陆注册使用mvp开发，就会发现MVP的高超之处
 * https://segmentfault.com/a/1190000003927200
 */
public class TokenPresenter extends BasePresenter {
    private static String TAG = "TOKENPRESENTER";
    MvpGetTokenView mMvpGetTokenView;


    public TokenPresenter(MvpGetTokenView activityView) {
        this.mMvpGetTokenView = activityView;
    }

    @Override
    public void attach(Context context) {
        super.attach(context);
    }

}
