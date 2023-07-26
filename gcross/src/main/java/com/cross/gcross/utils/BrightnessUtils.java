package com.cross.gcross.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.view.WindowManager;

public class BrightnessUtils {
    // 获取屏幕当前亮度
    public static int getScreenBrightness(Context context) {
        int brightness = 0;
        try {
            brightness = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return brightness;
    }

    // 设置屏幕亮度
    public static void setScreenBrightness(Context context, int brightness) {
        // 需要 "android.permission.WRITE_SETTINGS" 权限
        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brightness);

        // 更新系统设置
        ContentResolver resolver = context.getContentResolver();
        // 需要 "android.permission.WRITE_EXTERNAL_STORAGE" 权限
        Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        WindowManager.LayoutParams layoutParams = ((Activity) context).getWindow().getAttributes();
        layoutParams.screenBrightness = brightness / 255f;
        ((Activity) context).getWindow().setAttributes(layoutParams);
    }
}