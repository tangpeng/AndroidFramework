package com.onemore.goodproduct.util;

import android.os.Environment;

public class Constans {

    public static final int REQUEST_PERMISSION = 90;//权限申请

    public static final int FRAGINDEX = 79;
    public static final int FRAGMESSAGE = 80;
    public static final int FRAGME = 81;
    public static final int FRAGDIRECTOR = 82;

    public static final int VERI_TIME = 60000;//验证码倒计时

    public static final String MENGMENGCHICKFILEPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tangxiaopeng/";

    public static final int RESULT_SYSYTEM_OK = -1;// 获取系统的回调，相机，拍照，裁切都是成功为-1
    public static final int RESULT_OK = 1;// forResultActivity返回OK标志
    public static final int GET_CONTACT = 3;//获取联系人
    public static final int UPDATE_FUKA_INFO = 5;//修改福卡基本信息，回调
    public static final int UPDATE_INFO = 4;//修改个人信息，回调
    public static final int CITY_COUNTRY_RESULT = 6;//国家，城市回调
    public static final int BWH = 7;//三围
    public static final int PROFESSION = 8;//职业

}

