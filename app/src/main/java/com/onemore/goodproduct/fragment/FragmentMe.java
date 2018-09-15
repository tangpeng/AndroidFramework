package com.onemore.goodproduct.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.onemore.goodproduct.R;
import com.onemore.goodproduct.acitivity.LoginActivity;
import com.onemore.goodproduct.acitivity.SetActivity;
import com.onemore.goodproduct.view.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * state：
 * date:2018/8/26
 * code:https://github.com/tangpeng
 */
public class FragmentMe extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "FRAGMENTINDEX";
    @BindView(R.id.onClickLogin)
    Button onClickLogin;
    Unbinder unbinder;
    @BindView(R.id.ivFgmeHead)
    RoundImageView ivFgmeHead;
    @BindView(R.id.onClickSet)
    Button onClickSet;
    private View baseView;

    /**
     * Tab标题
     */
    public FragmentMe() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fg_me, container, false);
        unbinder = ButterKnife.bind(this, baseView);
        return baseView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void initData() {
    }


    @Override
    public void setListener(Context mContext) {
        onClickLogin.setOnClickListener(this);
        onClickSet.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.onClickSet:
                startActivity(SetActivity.class);
                break;
            case R.id.onClickLogin:
                startActivity(LoginActivity.class);
                break;
        }


    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}