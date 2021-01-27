package com.onemore.goodproduct.lmp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gw.library.ISkill;
import com.gw.library.Module;
import com.onemore.goodproduct.acitivity.WebSocketActivity;


/**
 * des:
 * author:lucas
 * date:2021/1/265:29 PM
 */
@Route(path = Module.MODULE_ONE)
public class ISkillImpl implements ISkill {

    Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public void getActivity() {
        context.startActivity(new Intent(context, WebSocketActivity.class));
    }
}