/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.onemore.goodproduct.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onemore.goodproduct.R;
import com.onemore.goodproduct.bean.IndexListBean;
import com.onemore.goodproduct.util.MyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YOLANDA on 2016/7/22.
 */
public class FindAdapter extends BaseAdapter<FindAdapter.ViewHolder> {
    private final String TAG="MainAdapter";
    private List<IndexListBean> mDataList=new ArrayList<>();

    public FindAdapter(Context context) {
        super(context);
    }

    @Override
    public void notifyDataSetChanged(Object dataList) {
        this.mDataList = (List<IndexListBean>) dataList;
        MyLog.i(TAG,"mDataList="+mDataList.size());
        super.notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(getInflater().inflate(R.layout.item_menu_main, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyLog.i(TAG,"TAG="+mDataList.get(position).getTitle());
        holder.setData(mDataList.get(position).getTitle());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
        public void setData(String title) {
            this.tvTitle.setText(title);
        }
    }

}
