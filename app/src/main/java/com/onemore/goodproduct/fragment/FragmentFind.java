package com.onemore.goodproduct.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.onemore.goodproduct.R;
import com.onemore.goodproduct.adapter.IndexLunboAdapter;
import com.onemore.goodproduct.bean.IndexLunboAdvBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * state：深圳好产品
 * date:2018/8/26
 * code:https://github.com/tangpeng
 */
public class FragmentFind extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "FragmentFind";
    @BindView(R.id.mLoopViewPager)
    com.onemore.goodproduct.view.LoopViewPager mLoopViewPager;
    @BindView(R.id.llviewpagerIndex)
    LinearLayout llviewpagerIndex;
    Unbinder unbinder;
    private View baseView;

    private IndexLunboAdapter mMyAdapter;

    private List<IndexLunboAdvBean> list = new ArrayList<>();//图片集合
    private List<View> views = new ArrayList<View>();//点的集合
    private LinearLayout.LayoutParams paramsL = new LinearLayout.LayoutParams(10, 10);//设置每个点容器大小
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
        IndexLunboAdvBean image = new IndexLunboAdvBean();
        image.setUrl("http://news.cnhubei.com/xw/yl/201405/W020140530279662501386.jpg");
        IndexLunboAdvBean image2 = new IndexLunboAdvBean();
        image2.setUrl("http://img0.imgtn.bdimg.com/it/u=3688010775,3049294081&fm=21&gp=0.jpg");
        IndexLunboAdvBean image3 = new IndexLunboAdvBean();
        image3.setUrl("http://npic7.edushi.com/cn/zixun/zh-chs/2015-09/09/4f4842aa50924e2bb6cedff42d09ef4a.png");
        list.add(image);
        list.add(image2);
        list.add(image3);

    }


    @Override
    public void setListener(Context mContext) {
        //图片集合，从后台直接返回，前端接收
        initMyPageAdapter(list);
        mLoopViewPager.autoLoop(true);
        //设置监听
        mLoopViewPager.setOnPageChangeListener(getListener());

    }

    @Override
    public void widgetClick(View v) {

    }
    /***
     * 初始化viewpager适配器
     *
     * @param imageBeanList
     */

    private void initMyPageAdapter(List<IndexLunboAdvBean> imageBeanList) {
        initPoint(imageBeanList);
        if (mMyAdapter == null) {
            mMyAdapter = new IndexLunboAdapter(getActivity(), imageBeanList);
            if (mLoopViewPager != null) {
                mLoopViewPager.setAdapter(mMyAdapter);
            }

        } else {
            mMyAdapter.upData(imageBeanList);
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


    /***
     * 初始化点
     * 可以根据图片多少自动增加点
     */
    private void initPoint(List<IndexLunboAdvBean> list) {
        views.clear();
        llviewpagerIndex.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            View view = new View(getActivity());
            //设置点的间距
            paramsL.setMargins(5, 0, 0, 0);
            view.setLayoutParams(paramsL);//设置点的颜色，默认从第一个开始
            if (i == 0) {
                view.setBackgroundResource(R.drawable.cricle_index_color);
            } else {
                view.setBackgroundResource(R.drawable.cricle_gray_color);
            }
            views.add(view);
            llviewpagerIndex.addView(view);
        }
    }

    /***
     * viewpager监听
     *
     * @return
     */
    private ViewPager.OnPageChangeListener getListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //相应图片被选中，相应点变成被选中色
                if (views.size() != 0 && views.get(position) != null) {
                    for (int i = 0; i < views.size(); i++) {
                        if (i == position) {
                            views.get(i).setBackgroundResource(R.drawable.cricle_index_color);
                        } else {
                            views.get(i).setBackgroundResource(R.drawable.cricle_gray_color);
                        }
                    }

                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        };
    }

}