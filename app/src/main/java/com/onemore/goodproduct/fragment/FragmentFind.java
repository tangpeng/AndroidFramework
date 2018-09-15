package com.onemore.goodproduct.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onemore.goodproduct.R;


/**
 * state：深圳好产品
 * date:2018/8/26
 * code:https://github.com/tangpeng
 */
public class FragmentFind extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "FragmentFind";
    private View baseView;
    /**
     * Tab标题
     */
    public FragmentFind() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fg_find, container, false);
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
        
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}