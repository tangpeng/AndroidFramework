package com.onemore.goodproduct.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * ProjectName:YiSai
 * Date:2017/8/25 13:50
 * Created by JY
 */

public class MyEditText extends android.support.v7.widget.AppCompatEditText {

    private onTextContextMenuItem mOnTextContextMenuItem;

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        final int ID_PASTE = android.R.id.paste;
        if (id == ID_PASTE) {
            if (mOnTextContextMenuItem != null){
                mOnTextContextMenuItem.onProcess();
            }
        }
        return super.onTextContextMenuItem(id);
    }

    public void setOnTextContextMenuItem(onTextContextMenuItem onTextContextMenuItem) {
        mOnTextContextMenuItem = onTextContextMenuItem;
    }

    public interface onTextContextMenuItem {
        void onProcess();
    }
}
