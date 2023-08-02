package com.cross.gcross.login;

import android.content.Context;

import com.cross.gcross.utils.GCrossHttpConstant;
import com.cross.gcross.utils.GCrossHttpUtils;
import com.tencent.mmkv.MMKV;

public class LoginClass {

    public static void login(String json, Context context) {
        String dir = context.getFilesDir().getAbsolutePath() + "/GcrossMMKV";
        MMKV.initialize(context, dir);
        GCrossSharedPreferencesUtil.getInstance();
        new GCrossHttpUtils(json, GCrossHttpConstant.LOGIN_GAME_USER).loginGameUser();
    }
}
