package com.onemore.goodproduct.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.gw.library.Logger;


/**
 * 深圳好产品
 * 封装的父类
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "BASEFRAGMENT";

    public BaseFragment() {
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        initData();
        setListener(getActivity());
        doBusiness();
        super.onActivityCreated(savedInstanceState);
    }


    /**
     *
     */
    public abstract void initData();


    /**
     * [绑定控件]
     *
     * @param resId
     * @returnindViewById只是查找到对象的引用，不应该叫做控件的实例化， 实例化是创建出一个新的对象
     * 当程序中控件数量太多时，findviewbyid代码将会很多，很繁琐，所以我们需要方法来简化他
     */
    protected <T extends View> T $(View mContextView, int resId) {
        return (T) mContextView.findViewById(resId);
    }

    /**
     * [设置监听]
     */
    public abstract void setListener(Context mContext);

    /**
     * View点击
     **/
    public abstract void widgetClick(View v);


    /**
     * [业务操作]
     */
    public abstract void doBusiness();

    @Override
    public void onClick(View v) {
        Logger.i(TAG,"onClick()");
        widgetClick(v);
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(getActivity(), clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Context context, Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * @param cls
     * @param requestCode
     */
    public void startActivityForResult(Context context, Class<?> cls,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        startActivityForResult(intent, requestCode);
    }

}
