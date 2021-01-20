package com.onemore.goodproduct.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onemore.goodproduct.R;
import com.onemore.goodproduct.acitivity.LoginActivity;


public class CommonTitleBar2 extends LinearLayout implements OnClickListener {
    private View mContainer;// 最外层布局
    private View mSystemTitleBar;
    private View mAppTitleBar;
    public TextView mTitleView; // 中间文案
    public View mDividerView;

    public TintImageTextView mLeftBtn; // 左边按钮
    public TintImageView mLeftSecondaryIcon; // 左边按钮
    public View mLeftTipsView;//左边红点

    public TintImageTextView mRightBtn; // 右边按钮
    public TintImageTextView mRightSecondaryIcon; // 右边按钮

    public BtnClickListener mCallback;// 左按钮点击事件监听器

    public CommonTitleBar2(Context context) {
        super(context);
        initView(context);
    }

    public CommonTitleBar2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public CommonTitleBar2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public int getLayout(){
        return R.layout.common_title_bar2;
    }

    public View initView(Context act) {
        View mView = LayoutInflater.from(act).inflate(getLayout(), this, true);
        mSystemTitleBar = mView.findViewById(R.id.system_title_bar);
        mAppTitleBar = mView.findViewById(R.id.app_title_bar);
        mContainer = mView.findViewById(R.id.title_bar);
        mTitleView = (TextView) mView.findViewById(R.id.app_title);
        mDividerView = mView.findViewById(R.id.title_divider);
        mLeftBtn = (TintImageTextView) mView.findViewById(R.id.title_left_btn);
        mLeftSecondaryIcon = (TintImageView) mView.findViewById(R.id.title_left_secondary_icon);
        mLeftBtn.setSelected(false);
        mRightBtn = (TintImageTextView) mView.findViewById(R.id.title_right_btn);
        mRightSecondaryIcon = (TintImageTextView) mView.findViewById(R.id.title_right_btn2);
        mLeftTipsView = mView.findViewById(R.id.left_tip_icon);

        mTitleView.setOnClickListener(this);
        mLeftBtn.setOnClickListener(this);
        mLeftSecondaryIcon.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);
        mRightSecondaryIcon.setOnClickListener(this);

        return mView;
    }

    public void enable(boolean enable) {
        if (mContainer != null)
            mContainer.setVisibility(enable ? View.VISIBLE : View.GONE);
    }

    private void setViewVisibility(View v, boolean visible) {
        v.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void onNightMode(boolean isNight) {
        if (isNight) {
            mSystemTitleBar.setBackgroundColor(getResources().getColor(R.color.color_night_bg));
            mAppTitleBar.setBackgroundColor(getResources().getColor(R.color.color_night_bg));
            mTitleView.setTextColor(getResources().getColor(R.color.color_f));
            mDividerView.setBackgroundColor(getResources().getColor(R.color.color_night_bg_dark));
        } else {
            mSystemTitleBar.setBackgroundColor(getResources().getColor(R.color.color_nav_bg));
            mAppTitleBar.setBackgroundColor(getResources().getColor(R.color.color_nav_bg));
            mTitleView.setTextColor(getResources().getColor(R.color.color_nav_title));
            mDividerView.setBackgroundColor(getResources().getColor(R.color.color_d));
        }
        mDividerView.setVisibility(GONE);
    }

    /**
     * 设置顶部statusbar间隔显示隐藏。默认是显示状态
     *
     * @param visible
     */
    public void setStatusBarView(boolean visible) {
        setViewVisibility(mSystemTitleBar, visible);
    }

    /**
     * 设置分割线显示隐藏。默认是显示状态
     *
     * @param visible
     */
    public void setDividerView(boolean visible) {
        setViewVisibility(mDividerView, visible);
    }

    /**
     * 设置左边按钮红点显示隐藏。默认是隐藏状态
     *
     * @param visible
     */
    public void setLeftTipsViewVisible(boolean visible){
        setViewVisibility(mLeftTipsView, visible);
    }


    /**
     * 设置中间文案的显示与隐藏
     *
     * @param isVisibility
     */
    public void mTitleViewVisibility(int isVisibility) {
        mTitleView.setVisibility(isVisibility);
    }



    /**
     * 设置中间title文案
     *
     * @param appTitleID 中间title文案ID
     */
    public void setAppTitle(int appTitleID) {
        if(getContext() instanceof Activity){
            ((Activity) getContext()).setTitle(appTitleID);
        }
        mTitleView.setText(appTitleID);
    }

    /**
     * 设置中间title文案
     *
     * @param appTitle 中间title文案
     */
    public void setAppTitle(String appTitle) {
        if(getContext() instanceof Activity){
            ((Activity) getContext()).setTitle(appTitle);
        }
        mTitleView.setText(appTitle);
    }

    /**
     * 设置中间title文案 加粗
     *
     * @param appTitle
     */
    public void setAppTitleBold(String appTitle) {
        if (getContext() instanceof Activity) {
            ((Activity) getContext()).setTitle(appTitle);
        }
        mTitleView.setText(appTitle);
        mTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    /**
     * 给左边按钮的文字
     *
     * @param titleID
     */
    public void setLeftResource(int titleID) {
//		if (0 != titleID) {
//			mLeftBtn.setText(AppMain.getAppString(titleID));
//		}
        mLeftBtn.setText("");
    }

    /**
     * 给左边按钮的文字
     *
     * @param title
     */
    public void setLeftResource(String title) {
//		mLeftBtn.setText(title);
        mLeftBtn.setText("");
    }

    /**
     * 左二 图标
     */
    public void setLeftSendaryIcon(int resId) {
        mLeftSecondaryIcon.setVisibility(VISIBLE);
        mLeftSecondaryIcon.setImageResource(resId);
    }

    /**
     * 给右边按钮的文字
     *
     * @param title
     */
    public void setRightResource(String title) {
        mRightBtn.setText(title);
        mRightBtn.setTextPadding(0,0,0,0);
    }

    /**
     * 给左边按钮设置图片和文案
     *
     * @param resourceID
     * @param titleID
     */
    public void setLeftResource(int resourceID, int titleID) {
        if (0 != resourceID) {
            mLeftBtn.setImageResource(resourceID);
        } else {
            mLeftBtn.setImageResource(0);
        }
        if (0 != titleID) {
            mLeftBtn.setText(titleID);
        } else {
            mLeftBtn.setText("");

        }
    }

    /**
     * 判断是否作为标题,需要字体变大,加粗
     */
    public void setLeftBold(boolean leftBold) {
        if (leftBold) {
            mLeftBtn.setTextSize(20);
            mLeftBtn.setTextBold(true);
        }
    }
    /**
     * 给右边按钮设置图片和文案
     *
     * @param resourceID
     * @param titleID
     */
    public void setRightResource(int resourceID, int titleID) {
        if (0 != resourceID) {
            mRightBtn.setImageResource(resourceID);
        }else{
            mRightBtn.setTextPadding(0,0,0,0);
        }
        if (0 != titleID) {
            mRightBtn.setText(titleID);
        }

        mRightBtn.setVisibility(View.VISIBLE);
    }

    /**
     * 给右边按钮设置图片和文案
     *
     * @param resourceID
     * @param titleID
     */
    public void setRightResource2(int resourceID, int titleID) {
        if (0 != resourceID) {
            mRightSecondaryIcon.setImageResource(resourceID);
        }
        if (0 != titleID) {
            mRightSecondaryIcon.setText(titleID);
        }

        mRightSecondaryIcon.setVisibility(View.VISIBLE);
    }

    /**
     * 给右边按钮设置文案
     *
     * @param titleID
     */
    public void setRightResource(int titleID) {
        if (titleID > 0) {
            mRightBtn.setText(titleID);
        } else {
            mRightBtn.setText(R.string.btn_back);
        }
        mRightBtn.setTextPadding(0,0,0,0);
        mRightBtn.setVisibility(View.VISIBLE);
    }

    /**
     * 设置左边按钮显示状态
     *
     * @param visible 是否显示
     */
    public void setLeftButtonVisible(boolean visible) {
        setViewVisibility(mLeftBtn, visible);
    }

    /**
     * 设置右边按钮显示状态
     *
     * @param visible 是否显示
     */
    public void setRightButton(boolean visible) {
        setViewVisibility(mRightBtn, visible);
    }

    /**
     * 设置右边按钮显示状态
     *
     * @param visible 是否显示
     */
    public void setRightButton2(boolean visible) {
        setViewVisibility(mRightSecondaryIcon, visible);
    }


    /**
     * 设置按钮点击监听事件
     *
     * @param mCallback
     */
    public void setBtnClickListener(BtnClickListener mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public void onClick(View v) {

        if (CommonUtils.isFastDoubleClick()) {
            return;
        }
        if (v.getId() == R.id.title_left_btn) {
            if (null != mCallback) {
                mCallback.onBtnClick(v.getId());
            } else {

                ((Activity) getContext()).finish();
            }
        } else {
            if (null != mCallback) {
                mCallback.onBtnClick(v.getId());
            }
        }
    }

    public void setLeftColorValue(int normal, int select) {
        mLeftBtn.setColorValue(normal,select);
    }
}
