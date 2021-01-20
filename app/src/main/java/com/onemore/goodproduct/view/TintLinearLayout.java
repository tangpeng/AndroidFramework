package com.onemore.goodproduct.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.onemore.goodproduct.R;


public class TintLinearLayout extends LinearLayout {

    private Context mContext;
    /**
     * 默认背景色
     */
    private int mBgNormalColor;
    /**
     * 选中背景色
     */
    private int mBgSelectColor;

    public TintLinearLayout(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public TintLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        obtainAttributeSet(context, attrs);
        init();
    }

    public TintLinearLayout(Context context, AttributeSet attrs) {
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

    /**
     * 设置图片选中和未选中状态的颜色值
     *
     * @param normal
     * @param select
     */
    public void setColorValue(int normal, int select) {
        mBgNormalColor = normal;
        mBgSelectColor = select;
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
    public void setSelected(boolean selected) {
        super.setSelected(selected);
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
