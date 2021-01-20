package com.onemore.goodproduct.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gw.library.Logger;
import com.onemore.goodproduct.R;
import com.onemore.goodproduct.util.ActivityManagers;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * state：深圳好产品
 * date:2018/8/26
 * code:https://github.com/tangpeng
 */
public class FragmentFind extends BaseFragment {
    private static final String TAG = "FragmentFind";


    Unbinder unbinder;

    private View baseView;

    /**
     * Tab标题
     */
    public FragmentFind() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fg_find, container, false);
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


    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness() {
        Logger.i(TAG, "doBusiness");

    }


    @OnClick({R.id.webSocket, R.id.DragGridView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.webSocket:
                ActivityManagers.WebSocketActivity(getActivity());
                break;
            case R.id.DragGridView:
                ActivityManagers.MyDragGridViewActivity(getActivity());
                break;
        }
    }
}