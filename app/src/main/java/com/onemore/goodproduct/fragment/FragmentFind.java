package com.onemore.goodproduct.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.onemore.goodproduct.R;
import com.onemore.goodproduct.adapter.FindAdapter;
import com.onemore.goodproduct.adapter.IndexLunboAdapter;
import com.onemore.goodproduct.adapter.MainAdapter;
import com.onemore.goodproduct.bean.IndexListBean;
import com.onemore.goodproduct.bean.IndexLunboAdvBean;
import com.onemore.goodproduct.mvpview.MvpUserActivityView;
import com.onemore.goodproduct.presenter.impl.UserPresenter;
import com.onemore.goodproduct.util.MyLog;
import com.onemore.goodproduct.util.Tools;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

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
public class FragmentFind extends BaseFragment implements View.OnClickListener, SwipeItemClickListener, MvpUserActivityView {
    private static final String TAG = "FragmentFind";
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    Unbinder unbinder;
    private View baseView;

    protected FindAdapter mAdapter;
    protected List<IndexListBean> mDataList;

    //mpv的框架,
    UserPresenter presenter;

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
        presenter = new UserPresenter(this);
        presenter.attach(getActivity());
        mAdapter = new FindAdapter(getActivity());
        mDataList = new ArrayList<>();
    }


    @Override
    public void setListener(Context mContext) {
        mRecyclerView.setLayoutManager(createLayoutManager());
        mRecyclerView.addItemDecoration(createItemDecoration());
        mRecyclerView.setSwipeItemClickListener(this);

        refreshLayout.setOnRefreshListener(mRefreshListener); // 刷新监听。
        mRecyclerView.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        mRecyclerView.setAdapter(mAdapter);


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

    }

    @Override
    public void doBusiness() {
        MyLog.i(TAG, "doBusiness");
        presenter.getFindData(getActivity());
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
    public void MVPFail(String data) {

    }

    @Override
    public void MVPSuccess(Object data) {
        MyLog.i(TAG, "MVPSuccess=" + data.toString());
        mDataList = (List<IndexListBean>) data;
        MyLog.i(TAG, "mDataList=" + mDataList.get(0).getTitle());

        mAdapter.notifyDataSetChanged(mDataList);
        mRecyclerView.loadMoreFinish(false, true);
    }
}