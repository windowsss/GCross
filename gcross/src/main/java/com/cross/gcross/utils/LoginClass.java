package com.cross.gcross.utils;

import android.content.Context;

import com.cross.gcross.utils.GCrossHttpConstant;
import com.cross.gcross.utils.GCrossHttpUtils;
import com.cross.gcross.utils.GCrossSharedPreferencesUtil;
import com.tencent.mmkv.MMKV;

public class LoginClass {

    public static void login(String json, Context context) {
        String dir = context.getFilesDir().getAbsolutePath() + "/zhongjianMMKV";
        MMKV.initialize(context, dir);
        GCrossSharedPreferencesUtil.getInstance();
        new GCrossHttpUtils(json, GCrossHttpConstant.LOGIN_GAME_USER).loginGameUser();
    }
}
