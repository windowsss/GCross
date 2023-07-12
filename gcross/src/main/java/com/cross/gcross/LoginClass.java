package com.cross.gcross;

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
//        Map<String, String> map = new ArrayMap<>();
//        map.put("gameUserOs", gameUserOs);
//        map.put("gameUserPk", gameUserPk);
////            map.put("gameUserName", "啊啊啊");
//        map.put("gameMediaPackage", packageName);
////            map.put("gameMediaPackage", "com.cncsys.blez");
//        map.put("gameUserGrade", gameUserGrade);
//        map.put("gameMediaIcon", "http://123.249.110.79:8080/jeecg-boot/sys/common/static/temp/切图_1689040494959.png");
//        map.put("gameUserOsVersion", GCrossUtils.getSystemVersion());
        new GCrossHttpUtils(json, GCrossHttpConstant.LOGIN_GAME_USER).loginGameUser(context);
    }
}
