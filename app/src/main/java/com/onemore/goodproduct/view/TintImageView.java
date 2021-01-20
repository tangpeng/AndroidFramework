package com.onemore.goodproduct.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.onemore.goodproduct.R;


public class TintImageView extends AppCompatImageView {

    private Context context;
    private int mNormalColor;
    private int mSelectColor;
    /**
     * 类型，是需要点击效果还是选中效果
     */
    private int mType = TintTextView.CLICK_TYPE;

    public TintImageView(Context context) {
        super(context);
        this.context = context;
    }

    public TintImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        obtainAttributeSet(context, attrs);
    }

    public TintImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        obtainAttributeSet(context, attrs);
    }

    /**
     * 一些属性设置
     *
     * @param c
     * @param attrs
     */
    private void obtainAttributeSet(Context c, AttributeSet attrs) {
        TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.TintView);

        mType = a.getInt(R.styleable.TintView_click_type, TintTextView.CLICK_TYPE);
        mNormalColor = a.getColor(R.styleable.TintView_image_normal_color, 0);
        mSelectColor = a.getColor(R.styleable.TintView_image_select_color, 0);

        if (mNormalColor != 0) {
            setColorFilter(mNormalColor);
        }

        a.recycle();
    }

    /**
     * 设置图片选中和未选中状态的颜色值
     *
     * @param normal
     * @param select
     */
    public void setColorValue(int normal, int select) {
        mNormalColor = normal;
        mSelectColor = select;

        if (mNormalColor != 0) {
            setColorFilter(mNormalColor);
        } else {
            setColorFilter(android.R.color.transparent);
        }
    }

    /**
     * 设置效果类型
     *
     * @param type
     */
    public void setClickType(int type) {
        mType = type;
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (mType !=TintTextView.CHECK_TYPE) {
            if (pressed) {
                if (mSelectColor != 0) {
                    setColorFilter(mSelectColor);
                }
            } else {
                if (!isSelected()) {
                    if (mNormalColor != 0) {
                        setColorFilter(mNormalColor);
                    } else {
                        setColorFilter(null);
                    }
                }
            }
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (mType !=TintTextView.CLICK_TYPE) {
            if (selected) {
                if (mSelectColor != 0) {
                    setColorFilter(mSelectColor);
                }
            } else {
                if (mNormalColor != 0) {
                    setColorFilter(mNormalColor);
                } else {
                    setColorFilter(null);
                }
            }
        }
    }

    private int dayNormalColor;

    public void onNightMode(boolean isNight) {
        if (isNight) {
            dayNormalColor = mNormalColor;
            //daySelectColor = mSelectColor;
            mNormalColor = 0xFFFFFFFF;
            //mSelectColor = 0xFFE6E6E6;
        } else {
            mNormalColor = dayNormalColor;
            //mSelectColor = daySelectColor;
        }
        //setColorValue(mNormalColor, mSelectColor);
        setColorFilter(mNormalColor);
    }
}
