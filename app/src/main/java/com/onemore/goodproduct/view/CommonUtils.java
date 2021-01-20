package com.onemore.goodproduct.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.gw.library.Logger;


/**
 * 用于判断第二次点击时是否太快，默认500ms只能点击一次，快速点击第二次不响应事件
 *
 * @author Administrator
 */
public class CommonUtils {
    private static long lastClickTime = 0;
    private static long minTimeInterval = 500;

    public  static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        Logger.i("start-time="+time);
        long duration = time - lastClickTime;
        lastClickTime = System.currentTimeMillis();
        Logger.i("end-time="+lastClickTime);
        if (duration > 0 && duration < minTimeInterval)
            return true;
        return false;
    }

    public synchronized static boolean isFasterDoubleClick() {
        long time = System.currentTimeMillis();
        long duration = time - lastClickTime;
        lastClickTime = System.currentTimeMillis();
        if (duration > 0 && duration < minTimeInterval + 500)
            return true;
        return false;
    }


    /**
     * 显示软键盘
     *
     * @param context
     * @param editText
     */
    public static void showSoftInput(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(editText, 0);
        }
    }
    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    public static void hideSoft(Activity activity) {
        if (null == activity) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        if (null != activity.getWindow().getDecorView().getWindowToken()) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public static void getMessage(int count, TextView ivMessage) {
        try {
            if (count > 0) {
                if (count > 99) {
                    ivMessage.setText("99+");
                } else {
                    ivMessage.setText(String.valueOf(count));
                }
                ivMessage.setVisibility(View.VISIBLE);
            } else {
                ivMessage.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            Logger.i("Exception");
            if(ivMessage!=null){
                ivMessage.setVisibility(View.GONE);
            }
        }
    };

    public static boolean filterMessage(String errorMessage){
        return true;
    }
}
