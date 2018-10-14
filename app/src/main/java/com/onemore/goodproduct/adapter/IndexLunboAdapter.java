package com.onemore.goodproduct.adapter;/**
 * Created by Administrator on 2017/10/14.
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.onemore.goodproduct.R;
import com.onemore.goodproduct.bean.IndexLunboAdvBean;
import com.onemore.goodproduct.util.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: tangxiaopeng
 * @Data: 2017/11/13 9:48
 * @desc： 首页轮播
 */
public class IndexLunboAdapter extends PagerAdapter {
    private List<IndexLunboAdvBean> list=new ArrayList<>();
    private Context context;
    public IndexLunboAdapter(Context context, List<IndexLunboAdvBean> list){
        this.list=list;
        this.context=context;
    }
    public void upData(List<IndexLunboAdvBean> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        //            return  bannerInfoList==null?0:bannerInfoList.size();
        return list==null?0:list.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        View view = View.inflate(context, R.layout.item_index_annunciate, null);
        ImageView iv_iamge = (ImageView) view.findViewById(R.id.ivIndexAnnunciateItem);
        //加载图片地址
        Tools.setImageByUrlGlide(context,list.get(position).getUrl(),iv_iamge, R.mipmap.ic_launcher);
        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

}

