package com.onemore.goodproduct.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onemore.goodproduct.R;
import com.onemore.goodproduct.adapter.MainAdapter;
import com.onemore.goodproduct.util.Tools;
import com.onemore.goodproduct.view.TitleBarView;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * state：首页
 * date:2018/10/14
 * code:https://github.com/tangpeng
 */
public class FragmentIndex extends BaseFragment implements View.OnClickListener, SwipeItemClickListener {
    private static final String TAG = "FRAGMENTINDEX";
    @BindView(R.id.title_bar)
    TitleBarView titleBar;
    Unbinder unbinder;
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private View baseView;

    protected MainAdapter mAdapter;
    protected List<String> mDataList;

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
        mDataList = createDataList();
        mAdapter = new MainAdapter(getActivity());
    }

    @Override
    public void setListener(Context mContext) {
        mRecyclerView.setLayoutManager(createLayoutManager());
        mRecyclerView.addItemDecoration(createItemDecoration());
        mRecyclerView.setSwipeItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged(mDataList);

        refreshLayout.setOnRefreshListener(mRefreshListener); // 刷新监听。
        mRecyclerView.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。

        // 第一次加载数据：一定要调用这个方法，否则不会触发加载更多。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        mRecyclerView.loadMoreFinish(false, true);
    }


    protected RecyclerView.ItemDecoration createItemDecoration() {
        return new DefaultItemDecoration(ContextCompat.getColor(getActivity(), R.color.divider_color));
    }

    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    protected List<String> createDataList() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add("第" + i + "个Item");
        }
        return dataList;
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

                    // 数据完更多数据，一定要掉用这个方法。
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
}