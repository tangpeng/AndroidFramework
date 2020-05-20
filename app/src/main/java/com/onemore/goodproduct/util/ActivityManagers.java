package com.onemore.goodproduct.util;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.onemore.goodproduct.acitivity.WebSocketActivity;

/**
 * des:
 * author:lucas
 * date:2020-05-2015:06
 */
public class ActivityManagers {


    public static void WebSocketActivity(FragmentActivity activity) {
        activity.startActivity(new Intent(activity , WebSocketActivity.class));
    }
}
