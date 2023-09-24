package com.cross.gcross.login;

import android.content.Context;

import com.cross.gcross.utils.DeviceIdUtils;
import com.cross.gcross.utils.GCrossHttpConstant;
import com.cross.gcross.utils.GCrossHttpUtils;
import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;

import java.util.Map;

public class LoginClass {

    public static void login(Map<String,String> map, Context context) {
        String dir = context.getFilesDir().getAbsolutePath() + "/GcrossMMKV";
        MMKV.initialize(context, dir);
        GCrossSharedPreferencesUtil.getInstance();
        map.put("gameUserPk", DeviceIdUtils.getDeviceId(context));
        new GCrossHttpUtils(new Gson().toJson(map), GCrossHttpConstant.LOGIN_GAME_USER).loginGameUser();
    }
}
