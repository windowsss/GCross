package com.cross.gcross;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cross.gcross.databinding.ActivityGcrossBinding;
import com.cross.gcross.fragment.GCrossIndexFragment;
import com.cross.gcross.fragment.GCrossQuestFragment;
import com.cross.gcross.fragment.GCrossShoppingFragment;
import com.cross.gcross.utils.GCrossSharedPreferencesUtil;
import com.cross.gcross.utils.LanguageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GCrossActivity extends AppCompatActivity  {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private ActivityGcrossBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGcrossBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        // 设置任务栏的字体颜色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if ("korean11".equals(GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.Language, "").toString())) {
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

    private void replaceFragment(int position) {
        if (position == 0) {
            binding.imageview.setVisibility(View.GONE);
            binding.rlLayout.setBackgroundColor(0xff004A80);
            binding.ivShopping.setImageResource(R.mipmap.shop_select);
            binding.ivIndex.setImageResource(R.mipmap.battle);
            binding.ivQuest.setImageResource(R.mipmap.reward);
        } else if (position == 1) {
            binding.imageview.setVisibility(View.VISIBLE);
            binding.rlLayout.setBackgroundColor(0);
            binding.ivShopping.setImageResource(R.mipmap.shop);
            binding.ivIndex.setImageResource(R.mipmap.battle_select);
            binding.ivQuest.setImageResource(R.mipmap.reward);
        } else if (position == 2) {
            binding.imageview.setVisibility(View.GONE);
            binding.rlLayout.setBackgroundColor(0xff004A80);
            binding.ivShopping.setImageResource(R.mipmap.shop);
            binding.ivIndex.setImageResource(R.mipmap.battle);
            binding.ivQuest.setImageResource(R.mipmap.reward_select);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            transaction.hide(fragmentList.get(i));
        }
        transaction.show(fragmentList.get(position));
        transaction.commit();
    }
}