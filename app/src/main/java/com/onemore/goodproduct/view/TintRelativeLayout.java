package com.onemore.goodproduct.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.gw.library.Logger;
import com.onemore.goodproduct.R;



public class TintRelativeLayout extends RelativeLayout {

    private Context mContext;
    /**
     * 默认背景色
     */
    private int mBgNormalColor;
    /**
     * 选中背景色
     */
    private int mBgSelectColor;

    public TintRelativeLayout(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public TintRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        obtainAttributeSet(context, attrs);
        init();
    }

    public TintRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        obtainAttributeSet(context, attrs);
        init();
    }

    /**
     * 一些属性设置
     *
     * @param c
     * @param attrs
     */
    private void obtainAttributeSet(Context c, AttributeSet attrs) {
        TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.TintView);

        mBgNormalColor = a.getColor(R.styleable.TintView_bg_normal_color, 0);
        mBgSelectColor = a.getColor(R.styleable.TintView_bg_select_color, 0);
        a.recycle();
    }

    private void init() {
        setBackgroundColor(mBgNormalColor);
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (pressed) {
            if (mBgSelectColor != 0) {
                setBackgroundColor(mBgSelectColor);
            }
        } else {
            if (!isSelected()) {
                if (mBgNormalColor != 0) {
                    setBackgroundColor(mBgNormalColor);
                }
            }
        }
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        Logger.i("lucas","selected="+selected);

        if (selected) {
            if (mBgSelectColor != 0) {
                setBackgroundColor(mBgSelectColor);
            }
        } else {
            if (mBgNormalColor != 0) {
                setBackgroundColor(mBgNormalColor);
            }
        }
    }

}
