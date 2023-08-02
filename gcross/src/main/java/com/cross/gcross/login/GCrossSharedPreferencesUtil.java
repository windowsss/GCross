package com.cross.gcross.login;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;


/**
 * SharedPreferences 工具类
 */
public class GCrossSharedPreferencesUtil {

    private static MMKV kv;
    public static final String TOKEN = "token";
    public static final String Language = "language";//语言
    public static final String GameUserId = "gameUserId";//用户ID
    public static final String applicationId = "applicationId";//媒体ID
    public static final String GameMediaId = "gameMediaId";//游戏ID
    public static final String ISAUTH = "isAuth";//是否授权(1 授权 0 未授权)

    /**
     * 初始化SharedPreferencesUtil,只需要初始化一次，建议在Application中初始化
     */
    public static void getInstance() {
        kv = MMKV.defaultMMKV();
    }


    /**
     * 保存数据到SharedPreferences
     *
     * @param key   键
     * @param value 需要保存的数据
     */
    public static void putData(String key, Object value) {
        boolean result;
        String type = value.getClass().getSimpleName();
        try {
            switch (type) {
                case "Boolean":
                    kv.encode(key, (Boolean) value);
                    break;
                case "Long":
                    kv.encode(key, (Long) value);
                    break;
                case "Float":
                    kv.encode(key, (Float) value);
                    break;
                case "String":
                    kv.encode(key, (String) value);
                    break;
                case "Integer":
                    kv.encode(key, (Integer) value);
                    break;
                default:
                    Gson gson = new Gson();
                    String json = gson.toJson(value);
                    kv.encode(key, json);
                    break;
            }
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
    }

    public static void remove(String... keys) {
        for (String key : keys) {
            kv.removeValueForKey(key);
        }
    }

    /**
     * 获取SharedPreferences中保存的数据
     *
     * @param key          键
     * @param defaultValue 获取失败默认值
     * @return 从SharedPreferences读取的数据
     */
    public static Object getData(String key, Object defaultValue) {
        Object result;
        String type = defaultValue.getClass().getSimpleName();
        try {
            switch (type) {
                case "Boolean":
                    result = kv.decodeBool(key, (Boolean) defaultValue);
                    break;
                case "Long":
                    result = kv.decodeLong(key, (Long) defaultValue);
                    break;
                case "Float":
                    result = kv.decodeFloat(key, (Float) defaultValue);
                    break;
                case "String":
                    result = kv.decodeString(key, (String) defaultValue);
                    break;
                case "Integer":
                    result = kv.decodeInt(key, (Integer) defaultValue);
                    break;
                default:
                    Gson gson = new Gson();
                    String json = kv.decodeString(key, "");
                    if (!TextUtils.isEmpty(json)) {
                        result = gson.fromJson(json, defaultValue.getClass());
                    } else {
                        result = defaultValue;
                    }
                    break;
            }
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }
}