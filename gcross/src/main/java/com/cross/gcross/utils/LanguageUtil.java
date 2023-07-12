package com.cross.gcross.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LanguageUtil {
    private static Locale mCurrentSystemLocal = Locale.ENGLISH;

    /**
     * 更改应用语言
     *
     * @param context
     * @param locale  语言地区
     */

    public static void changeAppLanguage(Context context, Locale locale) {
        Context appContext = context.getApplicationContext();
        Locale.setDefault(locale);
        Configuration configuration = appContext.getResources().getConfiguration();
        configuration.setLocale(locale);
        context.createConfigurationContext(configuration);
        Resources resources = appContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);//语言更换生效的代码!
    }

    /**
     * 设置语言
     */
    public static void setConfiguration(Context context, Locale targetLocale) {
        if (context == null) {
            return;
        }
//        Context appContext = getApplicationContext();
        Locale.setDefault(targetLocale);
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(targetLocale);
        context.createConfigurationContext(configuration);
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);//语言更换生效的代码!
    }

    public static Locale saveSystemCurrentLanguage(Context context) {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        mCurrentSystemLocal = locale;
        return locale;
    }

}