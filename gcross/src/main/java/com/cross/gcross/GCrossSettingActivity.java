package com.cross.gcross;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cross.gcross.databinding.ActivitySettingGcrossBinding;
import com.cross.gcross.login.GCrossSharedPreferencesUtil;
import com.cross.gcross.utils.LanguageUtil;

import java.util.Locale;

/**
 * 设置
 */
public class GCrossSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySettingGcrossBinding binding = ActivitySettingGcrossBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        initToolbarIndex(getResources().getString(R.string.setting));
        binding.tvChinese.setOnClickListener(v -> {
            LanguageUtil.changeAppLanguage(this, Locale.SIMPLIFIED_CHINESE);
//            GCrossActivity.mainActivity.finish();
            GCrossSharedPreferencesUtil.putData(GCrossSharedPreferencesUtil.Language, "chinese");
            Intent intent = new Intent(GCrossSettingActivity.this, GCrossActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
        binding.tvEnglish.setOnClickListener(v -> {
//            GCrossActivity.mainActivity.finish();
            GCrossSharedPreferencesUtil.putData(GCrossSharedPreferencesUtil.Language, "korean");
            LanguageUtil.changeAppLanguage(this, Locale.ENGLISH);
            Intent intent = new Intent(GCrossSettingActivity.this, GCrossActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}