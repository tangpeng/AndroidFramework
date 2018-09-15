package com.onemore.goodproduct.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Tools {


    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年

    private static final String TAG = "Tools";
    private static final String NOT_LOGIN = "notlogin";
    private static Context context;
    private static Toast toast;

    public Tools(Context context) {
        this.context = context;
    }


    /**
     * 检查输入的邮箱是否符合E-mail格式
     *
     * @param email E-mail地址
     * @return
     */
    public static boolean isEmail(String email) {
        Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 防止每一次点击,都创建一个新的,静态内部类在外面,那么activity,相当于单列模式,需要使用,context.getApplicationContext()
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        if (toast != null) {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        } else {
            toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        }
        if (context != null) {
            if (!((Activity) context).isFinishing())
                toast.show();
        }
    }

    /**
     * 取得当前时间
     *
     * @return
     */
    public static String getNowTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar
                .getInstance().getTime());
    }

    /**
     * 檢查是否有网络连接
     *
     * @return
     */
    public static boolean hasNetwork(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 显示进度条对话框
     *
     * @param paramContext
     * @param paramString
     */

    public static void showInfoDialog(Context paramContext, String paramString) {
        showInfoDialog(paramContext, paramString, "确定", null); // "温馨提示",
    }

    public static void showInfoDialog(Context paramContext,
                                      String paramString1, String paramString3,
                                      DialogInterface.OnClickListener paramOnClickListener) {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
        // localBuilder.setTitle(paramString2);
        localBuilder.setMessage(paramString1);
        if (paramOnClickListener == null)
            paramOnClickListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface paramDialogInterface,
                                    int paramInt) {
                }
            };
        localBuilder.setPositiveButton(paramString3, paramOnClickListener);
        localBuilder.show();
    }


    /**
     * 获取设备diviceID
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        String mGetDeviceId = "";
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            mGetDeviceId = tm.getDeviceId();
        } catch (RuntimeException e) {
            mGetDeviceId = "手机权限未开启，获取失败";
        }
        return mGetDeviceId;
    }

    /**
     * 获取设备的宽度 // android获取屏幕的高度和宽度用到WindowManager
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    /**
     * 获取设备的高度// android获取屏幕的高度和宽度用到WindowManager
     *
     * @param context
     * @return
     */
    public static int getScreenHight(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        MyLog.v("dbw", "Status height:" + height);
        return height;
    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    //获取到包名
    public static String getPackName(Context context) {
        return getPackageInfo(context).packageName;
    }


    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    public static String getUUID() {
        //格式bbdffdda-1e72-4d41-9ae3-77057ea4c5e3//一共36位，去掉4个“-”刚好32 作为加密的key
        String uuidStr = UUID.randomUUID().toString().replace("-", "");
//		uuidStr = uuidStr.substring(0, 8) + uuidStr.substring(9, 13)
//				+ uuidStr.substring(14, 18) + uuidStr.substring(19, 23)
//				+ uuidStr.substring(24);
        return uuidStr;
    }


    public static String getAgentsId() {
        String agemtId = "";
        return agemtId;
    }

    ;

    /**
     * 生成随机文件名：当前年月日时分秒+五位随机数
     *
     * @return
     */
    public static String getRandomFileName(String type) {
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
        return str + rannum + type;// 当前时间
    }

    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    public static String getTimeFormatText(Date date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }




    /**
     * 获取输入框的长度
     *
     * @param text
     * @return
     */
    public static int getEditTextLength(EditText text) {
        return text.getText().toString().trim().length();
    }


    /*
     * 将时间转换为时间戳
     */
    public static String StampTodate(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = Long.parseLong(s) * 1000;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    /*
     * 将时间戳转换为时间 ,带上时分秒 暂时不用
     */
    public static String dateToStampTime(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = Long.parseLong(s) * 1000;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    /**
     * 设置获取到图片的名字 时间戳+五位随机数
     *
     * @return
     */
    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());// 获取当前的系统的时间
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        return "android_" + dateFormat.format(date) + (int) ((Math.random() * 9 + 1) * 10000) + ".jpg";
    }

    /**
     * 设置获取到图片的名字 时间戳+五位随机数
     *
     * @return
     */
    public static String getVideoFileName() {
        Date date = new Date(System.currentTimeMillis());// 获取当前的系统的时间
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        return "android_" + dateFormat.format(date) + (int) ((Math.random() * 9 + 1) * 10000) + ".mp4";
    }

    /**
     * 数字超过一万，保留一位有效数字
     *
     * @param d
     * @return
     */
    public static String KeepValidNumber(int d) {
        if (d < 10000) {
            return d + "";
        } else {
            DecimalFormat df = new DecimalFormat("#.0");
            return df.format(d / 10000) + "万";
        }

    }

    /**
     * h5页面如果后面 Android加1 iOS加2
     *
     * @return
     */
    public static String typeDevice() {
        return "/1";
    }


    /**
     * 使用glide加载网络数据
     *
     * @param context
     * @param url
     * @param mImageView
     */
    public static void setImageByUrlGlide(Context context, String url, ImageView mImageView, int resourceId) {
        Glide.with(context)
                .load(url)
                .asBitmap()//必须要有的，不然会导致图片显示在控件上面，位置有问题
                .placeholder(resourceId)
                .centerCrop()
//                .skipMemoryCache(true)//跳过内存缓存。
//                .diskCacheStrategy(DiskCacheStrategy.NONE)//不要在disk硬盘中缓存。
                .into(mImageView);
    }

    /**
     * 艺人相册，使用缩略图就好了，无需使用原图
     * @param context
     * @param url
     * @param mImageView
     */
    public static void setImageThumbByUrlGlide(Context context, String url, ImageView mImageView, int resourceId) {
        //用原图的1/10作为缩略图
        Glide.with(context)
                .load(url)
                .asBitmap()//必须要有的，不然会导致图片显示在控件上面，位置有问题
                .thumbnail(0.5f)
//               .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存源资源和转换后的资源
                .placeholder(resourceId).error(resourceId)
                .into(mImageView);
    }


}
