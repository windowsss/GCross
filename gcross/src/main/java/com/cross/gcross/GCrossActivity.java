package com.cross.gcross;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cross.gcross.databinding.ActivityGcrossBinding;
import com.cross.gcross.fragment.GCrossIndexFragment;
import com.cross.gcross.fragment.GCrossQuestFragment;
import com.cross.gcross.fragment.GCrossShoppingFragment;
import com.cross.gcross.login.GCrossSharedPreferencesUtil;
import com.cross.gcross.utils.LanguageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GCrossActivity extends AppCompatActivity implements GCrossIndexFragment.CallListener, GCrossShoppingFragment.CallShoppingListener {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private ActivityGcrossBinding binding;
    private final int REQUEST_CODE_WRITE_SETTINGS_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGcrossBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if ("0".equals(GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.ISAUTH, ""))) {
            finish();
        }
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        // 设置任务栏的字体颜色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (!Settings.System.canWrite(this)) {
            // 如果权限还未授予，启动设置界面让用户授权
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQUEST_CODE_WRITE_SETTINGS_PERMISSION);
        } else {
            // 已经授予WRITE_SETTINGS权限，可以进行操作
            // TODO: 执行需要WRITE_SETTINGS权限的操作
        }
        if ("korean".equals(GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.Language, "").toString())) {
            LanguageUtil.setConfiguration(this, Locale.KOREAN);
        } else {
            LanguageUtil.setConfiguration(this, Locale.SIMPLIFIED_CHINESE);
        }
        fragmentList.add(GCrossShoppingFragment.newInstance());
        fragmentList.add(GCrossIndexFragment.newInstance());
        fragmentList.add(GCrossQuestFragment.newInstance());
        initFragment();
        replaceFragment(1);
        binding.ivShopping.setOnClickListener(v -> replaceFragment(0));
        binding.ivIndex.setOnClickListener(v -> replaceFragment(1));
        binding.ivQuest.setOnClickListener(v -> replaceFragment(2));
    }

    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            transaction.add(R.id.frameLayout, fragmentList.get(i));
        }
        transaction.commit();
    }

    // 在Activity的onActivityResult方法中处理权限请求结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_WRITE_SETTINGS_PERMISSION) {
            // 检查权限是否已经授予
            Settings.System.canWrite(this);// 已经授予WRITE_SETTINGS权限，可以进行操作
// TODO: 执行需要WRITE_SETTINGS权限的操作
// 用户未授予WRITE_SETTINGS权限，需要做相应处理
        }
    }

    private void replaceFragment(int position) {
        if (position == 0) {
            binding.imageview.setVisibility(View.GONE);
            binding.rlLayout.setBackgroundColor(0xff004A80);
            setImage(R.mipmap.shop_select, R.mipmap.battle, R.mipmap.reward, R.mipmap.icon_sc_xz, R.mipmap.icon_zd_wxz, R.mipmap.icon_bscdz_wxz);
        } else if (position == 1) {
            binding.imageview.setVisibility(View.VISIBLE);
            binding.rlLayout.setBackgroundColor(0);
            setImage(R.mipmap.shop, R.mipmap.battle_select, R.mipmap.reward, R.mipmap.icon_sc_wxz, R.mipmap.icon_zd_xz, R.mipmap.icon_bscdz_wxz);
        } else if (position == 2) {
            binding.imageview.setVisibility(View.GONE);
            binding.rlLayout.setBackgroundColor(0xff004A80);
            setImage(R.mipmap.shop, R.mipmap.battle, R.mipmap.reward_select, R.mipmap.icon_sc_wxz, R.mipmap.icon_zd_wxz, R.mipmap.icon_bscdz_xz);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            transaction.hide(fragmentList.get(i));
        }
        transaction.show(fragmentList.get(position));
        transaction.commit();
    }

    private void setImage(int shopping, int index, int quest, int shoppingKorean, int indexKorean, int questKorean) {
        if ("korean".equals(GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.Language, "").toString())) {
            binding.ivShopping.setImageResource(shoppingKorean);
            binding.ivIndex.setImageResource(indexKorean);
            binding.ivQuest.setImageResource(questKorean);
        } else {
            binding.ivShopping.setImageResource(shopping);
            binding.ivIndex.setImageResource(index);
            binding.ivQuest.setImageResource(quest);
        }
    }


    @Override
    public void startQuest() {
        replaceFragment(2);
    }

    @Override
    public void startQuestShopping() {
        replaceFragment(2);
    }
}