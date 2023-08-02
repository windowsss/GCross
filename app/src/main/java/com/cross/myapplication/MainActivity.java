package com.cross.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.ArrayMap;

import com.cross.gcross.GCrossActivity;
import com.cross.gcross.LoginClass;
import com.cross.gcross.utils.GCrossUtils;
import com.google.gson.Gson;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    PackageInfo packageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            PackageManager pm = this.getPackageManager();
            packageInfo = pm.getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, String> map = new ArrayMap<>();
        map.put("gameUserOs", "CROSS_AOS");
        map.put("gameUserPk", "2");
//            map.put("gameUserName", "啊啊啊");
        map.put("gameMediaPackage", packageInfo.packageName);
//        map.put("gameMediaPackage", "com.cncsys.blez");
        map.put("gameUserGrade", "20.35");
        map.put("gameMediaIcon", "http://123.249.110.79:8080/jeecg-boot/sys/common/static/temp/qt_1689040494959_1689261898778.png");
        map.put("gameUserOsVersion", GCrossUtils.getSystemVersion());
        LoginClass.login(new Gson().toJson(map), this);
        findViewById(R.id.tvStart).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, GCrossActivity.class)));
//            new GCrossHttpUtils(new Gson().toJson(map), "loginGameUser").loginGameUser();
//        startActivity(new Intent(MainActivity.this, GCrossActivity.class));
    }
}