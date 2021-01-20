package com.onemore.goodproduct.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.onemore.goodproduct.R;

public class TintTextView extends android.support.v7.widget.AppCompatTextView {

    public final static int CLICK_TYPE = 0;
    public final static int CHECK_TYPE = 1;
    public final static int BOTH_TYPE = 2;
    private Context context;
    private int mBgNormalColor;
    private int mBgSelectColor;
    private int mNormalColor;
    private int mSelectColor;
    /**
     * 类型，是需要点击效果还是选中效果
     */
    private int mType = CLICK_TYPE;

    private Drawable drawableRight;

    public TintTextView(Context context) {
        super(context);
        this.context = context;
    }

    public TintTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        obtainAttributeSet(context, attrs);
    }

    public TintTextView(Context context, AttributeSet attrs) {
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

        mType = a.getInt(R.styleable.TintView_click_type, CLICK_TYPE);
        mBgNormalColor = a.getColor(R.styleable.TintView_bg_normal_color, 0);
        mBgSelectColor = a.getColor(R.styleable.TintView_bg_select_color, 0);
        mNormalColor = a.getColor(R.styleable.TintView_text_normal_color, 0);
        mSelectColor = a.getColor(R.styleable.TintView_text_select_color, 0);
        if (mNormalColor != 0) {
            setTextColor(mNormalColor);
        }
        if (mBgNormalColor != 0) {
            setBackgroundColor(mBgNormalColor);
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
        setTextColor(mNormalColor);
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
        if (mType != CHECK_TYPE) {
            if (pressed) {
                if (mSelectColor != 0) {
                    setTextColor(mSelectColor);
                }
                if (mBgSelectColor != 0) {
                    setBackgroundColor(mBgSelectColor);
                }
            } else {
                if (!isSelected()) {
                    if (mNormalColor != 0) {
                        setTextColor(mNormalColor);
                    }
                    if (mBgNormalColor != 0) {
                        setBackgroundColor(mBgNormalColor);
                    }
                }
            }
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (mType != CLICK_TYPE) {
            if (selected) {
                if (mSelectColor != 0) {
                    setTextColor(mSelectColor);
                }
                if (mBgSelectColor != 0) {
                    setBackgroundColor(mBgSelectColor);
                }
            } else {
                if (mNormalColor != 0) {
                    setTextColor(mNormalColor);
                }
                if (mBgNormalColor != 0) {
                    setBackgroundColor(mBgNormalColor);
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //DrawableRight自定义
        if (drawableRight != null) {
            Drawable drawableLeft = drawableRight;
            //float textWidth = getTextWidth(getPaint(),getText().toString());
            float textWidth = getPaint().measureText(getText().toString());
            float textHeight = getPaint().measureText("码".subSequence(0, 1).toString());
            int drawablePadding = getCompoundDrawablePadding();
            int drawableWidth = 0;
            drawableWidth = drawableLeft.getIntrinsicWidth();
            float bodyWidth = textWidth + drawableWidth + drawablePadding;
            canvas.translate(bodyWidth / 2 + textHeight + drawableWidth / 3, textHeight);
            drawableLeft.draw(canvas);
        }
    }

    /**
     * DrawableRight自定义
     *
     * @param drawableRight
     */
    public void setDrawableRight(Drawable drawableRight) {
        this.drawableRight = drawableRight;
        if (this.drawableRight != null) {
            this.drawableRight.setBounds(0, 0, drawableRight.getIntrinsicWidth(), drawableRight.getIntrinsicHeight());
        }
    }
}
