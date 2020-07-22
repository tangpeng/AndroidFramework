package com.onemore.goodproduct.fragment;


import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.onemore.goodproduct.R;
import com.onemore.goodproduct.acitivity.WebSocketActivity;
import com.onemore.goodproduct.adapter.IndexLunboAdapter;
import com.onemore.goodproduct.adapter.MainAdapter;
import com.onemore.goodproduct.bean.IndexBean;
import com.onemore.goodproduct.bean.IndexListBean;
import com.onemore.goodproduct.bean.IndexLunboAdvBean;
import com.onemore.goodproduct.bean.IndexLunboBean;
import com.onemore.goodproduct.mvpview.MvpUserActivityView;
import com.onemore.goodproduct.presenter.impl.UserPresenter;
import com.onemore.goodproduct.util.ActivityManagers;
import com.onemore.goodproduct.util.GsonTools;
import com.onemore.goodproduct.util.MyLog;
import com.onemore.goodproduct.util.Tools;
import com.onemore.goodproduct.view.LoopViewPager;
import com.onemore.goodproduct.view.TitleBarView;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.R.id.list;

/**
 * state：首页
 * date:2018/10/14
 * code:https://github.com/tangpeng
 */
public class FragmentIndex extends BaseFragment implements View.OnClickListener, SwipeItemClickListener, MvpUserActivityView {
    private static final String TAG = "FRAGMENTINDEX";
    @BindView(R.id.title_bar)
    TitleBarView titleBar;
    Unbinder unbinder;
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private View baseView;

    private View headView;
    LoopViewPager mLoopViewPager;
    LinearLayout llviewpagerIndex;
    Button webSocket;
    Button MyDragGridView;

    protected MainAdapter mAdapter;
    protected List<IndexBean.DatasBean> mDataList;


    //mpv的框架,
    UserPresenter presenter;

    private IndexLunboAdapter mMyAdapter;
    private List<IndexLunboAdvBean> list = new ArrayList<>();//图片集合
    private List<View> views = new ArrayList<View>();//点的集合
    private LinearLayout.LayoutParams paramsL = new LinearLayout.LayoutParams(10, 10);//设置每个点容器大小

    /**
     * Tab标题
     */
    public FragmentIndex() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fg_index, container, false);
        unbinder = ButterKnife.bind(this, baseView);
        return baseView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void initData() {
        presenter = new UserPresenter(this);
        presenter.attach(getActivity());
        mAdapter = new MainAdapter(getActivity());
        mDataList = new ArrayList<>();

        IndexLunboAdvBean image = new IndexLunboAdvBean();
        image.setUrl("https://img.gtsuat.com/source/public/info/other/6.jpg");
        IndexLunboAdvBean image2 = new IndexLunboAdvBean();
        image2.setUrl("https://img.gtsuat.com/source/public/info/other/4.jpg");
        IndexLunboAdvBean image3 = new IndexLunboAdvBean();
        image3.setUrl("https://img.gtsuat.com/source/public/info/other/8.jpg");
        list.add(image);
        list.add(image2);
        list.add(image3);

    }

    @Override
    public void setListener(Context mContext) {
        mRecyclerView.setLayoutManager(createLayoutManager());
        mRecyclerView.addItemDecoration(createItemDecoration());
        mRecyclerView.setSwipeItemClickListener(this);

        refreshLayout.setOnRefreshListener(mRefreshListener); // 刷新监听。
        mRecyclerView.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        headView = getActivity().getLayoutInflater().inflate(R.layout.including_head_index, null);
        webSocket = headView.findViewById(R.id.webSocket);
        MyDragGridView = headView.findViewById(R.id.DragGridView);
        mLoopViewPager = headView.findViewById(R.id.mLoopViewPager);
        llviewpagerIndex = headView.findViewById(R.id.llviewpagerIndex);
        mRecyclerView.addHeaderView(headView);
        mRecyclerView.setAdapter(mAdapter);

        webSocket.setOnClickListener(this);
        MyDragGridView.setOnClickListener(this);

        //图片集合，从后台直接返回，前端接收
        initMyPageAdapter(list);
        mLoopViewPager.autoLoop(true);
        //设置监听
        mLoopViewPager.setOnPageChangeListener(getListener());

    }


    protected RecyclerView.ItemDecoration createItemDecoration() {
        return new DefaultItemDecoration(ContextCompat.getColor(getActivity(), R.color.divider_color));
    }

    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }


    @Override
    public void onItemClick(View itemView, int position) {
        Tools.showToast(getActivity(), "第" + position + "个");
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.webSocket:
                ActivityManagers.WebSocketActivity(getActivity());
                break;
            case R.id.DragGridView:
                ActivityManagers.MyDragGridViewActivity(getActivity());
                break;
        }
    }

    @Override
    public void doBusiness() {
        MyLog.i(TAG, "doBusiness");
        presenter.getIndexData(getActivity());
    }


    /**
     * 刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(false);
//                    loadData();
                }
            }, 1000); // 延时模拟请求服务器。
        }
    };

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            mRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {

                    // notifyItemRangeInserted()或者notifyDataSetChanged().
//                    mAdapter.notifyItemRangeInserted(mDataList.size() - strings.size(), strings.size());
                    // 请求数据，并更新数据源操作。
                    mAdapter.notifyDataSetChanged();
                    // 数据完更多数据，一定要调用这个方法。
                    // 第一个参数：表示此次数据是否为空。
                    // 第二个参数：表示是否还有更多数据。
                    mRecyclerView.loadMoreFinish(false, true);

                    // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
                    // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
                    // errorMessage是会显示到loadMoreView上的，用户可以看到。
                    // mRecyclerView.loadMoreError(0, "请求网络失败");
                }
            }, 1000);
        }
    };

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
    public void MVPFail(String data) {

    }

    @Override
    public void MVPSuccess(Object data) {

        IndexBean mindexBean = (IndexBean) data;
        mDataList = mindexBean.getDatas();
        MyLog.i(TAG, "MVPSuccess=" + mDataList.toString());
        mAdapter.notifyDataSetChanged(mDataList);
        mRecyclerView.loadMoreFinish(false, true);

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
}