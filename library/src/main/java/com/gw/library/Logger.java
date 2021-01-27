package com.gw.library;

import android.util.Log;

/**
 * 日志答应控制类
 * Created by aries.zhan on 2016/6/20.
 */
public class Logger {
    public static int ERROR = 1;
    public static int WARN = 2;
    public static int INFO = 3;
    public static int DEBUG = 4;
    public static int VERBOS = 5;
    public static String TAG = "login_";
    private static int LOG_LEVEL = 0;  //>6 打印所有日志

    public static void e(String tag, String msg) {
        if (LOG_LEVEL > ERROR) {
            //System.out.println("线程id = " + android.os.Process.myTid());
            Log.e(TAG+tag, msg);
        }
    }

    /**
     * 打印出错信息的 StackTrace 信息
     * <p>
     * ( 若调试开关关闭，则不打印 )
     */
    public static void e(String tag, Throwable e) {
        if (null != e && LOG_LEVEL > ERROR) {
            if (e instanceof Throwable) {
                e.printStackTrace();
            }
        }
    }

    public static void w(String tag, String msg) {
        if (LOG_LEVEL > WARN) {
            //System.out.println("i=线程id = " + android.os.Process.myTid());
            Log.w(TAG+tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LOG_LEVEL > INFO) {
            //System.out.println("i=线程id = " + android.os.Process.myTid());
            Log.i(TAG+tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LOG_LEVEL > DEBUG) {
           // System.out.println("i=线程id = " + android.os.Process.myTid());
            Log.d(TAG+tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (LOG_LEVEL > VERBOS) {
           // System.out.println("i=线程id = " + android.os.Process.myTid());
            Log.v(TAG+tag, msg);
        }
    }

    public static void ableLogger() {
        LOG_LEVEL = 6;
    }

    public static void e(Throwable e) {
        e(TAG, e);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void v(String msg) {
        v(TAG, msg);
    }
}
