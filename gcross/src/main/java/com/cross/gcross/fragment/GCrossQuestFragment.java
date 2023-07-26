package com.cross.gcross.fragment;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.cross.gcross.R;
import com.cross.gcross.WebViewActivity;
import com.cross.gcross.adapter.BannerViewAdapter;
import com.cross.gcross.adapter.QuestAdapter;
import com.cross.gcross.base.GCrossBaseFragment;
import com.cross.gcross.bean.CrossBannerBean;
import com.cross.gcross.bean.CrossGameMediaBean;
import com.cross.gcross.databinding.FragmentQuestGcrossBinding;
import com.cross.gcross.dialog.WarmReminderDialog;
import com.cross.gcross.utils.EventList;
import com.cross.gcross.utils.GCrossHttpConstant;
import com.cross.gcross.utils.GCrossHttpUtils;
import com.cross.gcross.utils.GCrossSharedPreferencesUtil;
import com.google.gson.Gson;
import com.youth.banner.indicator.CircleIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 任务  宝石充电站
 */
public class GCrossQuestFragment extends GCrossBaseFragment {
    private FragmentQuestGcrossBinding questBinding;
    private QuestAdapter questAdapter;
    private final List<CrossGameMediaBean.ResultBean> resultBeans = new ArrayList<>();
    private Map<String, String> map;
    private CrossBannerBean.ResultBean resultBean;

    /**
     * 点击切换Fragment会调用
     */
    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            //注销事件
            EventBus.getDefault().unregister(this);
        }
    }

    public static GCrossQuestFragment newInstance() {
        return new GCrossQuestFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        questBinding = FragmentQuestGcrossBinding.inflate(getLayoutInflater());
        if (!EventBus.getDefault().isRegistered(this)) {
            //注册事件
            EventBus.getDefault().register(this);
        }
        refreshData();
        map = new ArrayMap<>();
        map.put("applicationId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.applicationId, "").toString());
        map.put("gameUserOs", "CROSS_AOS");
        new GCrossHttpUtils(new Gson().toJson(map), GCrossHttpConstant.getCrossBanners).getCrossBanners();//获取轮播图
        //游戏介绍
        questBinding.ivGame.setOnClickListener(v -> {
            questBinding.rlThumbnail.setVisibility(View.GONE);
            questBinding.ivBottom.setVisibility(View.VISIBLE);
        });
        //游戏详细介绍
        questBinding.ivBottom.setOnClickListener(v -> {
            questBinding.rlThumbnail.setVisibility(View.VISIBLE);
            questBinding.ivBottom.setVisibility(View.GONE);
        });
        questAdapter = new QuestAdapter(R.layout.adapter_quest, resultBeans);
        questBinding.rlList.setAdapter(questAdapter);
        questAdapter.setOnItemClickListener((adapter, view, position) -> {
            String btnStatus = questAdapter.getData().get(position).getBtnStatus();
            //1玩游戏2领取奖励3去领取
            if ("1".equals(btnStatus)) {
                //唤醒安装app  跳转到指定页面
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(questAdapter.getData().get(position).getGameMediaPackageAos(), questAdapter.getData().get(position).getGamMediaActivityUrlAos()));
                startActivity(intent);
            } else if ("2".equals(btnStatus)) {
                map = new ArrayMap<>();
                map.put("gameUserId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.GameUserId, "").toString());
                map.put("gameUserOs", "CROSS_AOS");
                map.put("applicationId",GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.applicationId, "").toString());
                map.put("activityId", questAdapter.getData().get(position).getActivityId());
                new GCrossHttpUtils(new Gson().toJson(map), GCrossHttpConstant.saveCrossGameMediaDiamond).saveCrossGameMediaDiamond();
            } else {
                //跳转到应用商店指定的app详情页面
                String marketId = "market://details?id=" + questAdapter.getData().get(position).getGameMediaPackageAos();
                Uri uri = Uri.parse(marketId);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        //使用条款
        questBinding.tvClauseContent.setOnClickListener(v -> {
            if (resultBean != null) {
                startActivity(new Intent(getActivity(), WebViewActivity.class).putExtra("type", "11").putExtra("text", resultBean.getClauseContent()));
            }
        });
        //隐私政策
        questBinding.tvPrivacyPolicyContent.setOnClickListener(v -> {
            if (resultBean != null) {
                startActivity(new Intent(getActivity(), WebViewActivity.class).putExtra("type", "11").putExtra("text", resultBean.getPrivacyPolicyContent()));
            }
        });
        questBinding.tvResumeGame.setOnClickListener(v -> requireActivity().finish());
        return questBinding.getRoot();
    }

    //任务列表领取免费钻石
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void saveCrossGameMediaDiamond(EventList.saveCrossGameMediaDiamond event) {
        try {
            JSONObject jsonObject = new JSONObject(event.response);
            if ("200".equals(jsonObject.getString("code"))) {
                WarmReminderDialog.Builder warmPromptDialog = new WarmReminderDialog.Builder(getActivity());
                warmPromptDialog.setData(jsonObject.getString("result"));
                refreshData();
                refreshDataIndex();
                refreshDataShopping();
                Dialog noticeDialog = warmPromptDialog.create();
                noticeDialog.setCancelable(false);
                noticeDialog.show();
                Window window = noticeDialog.getWindow();
                assert window != null;
                WindowManager.LayoutParams lp = window.getAttributes();
                WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
                int height = wm.getDefaultDisplay().getHeight();
                int b = height - (height / 2);
                lp.gravity = Gravity.CENTER;
                lp.width = ViewGroup.LayoutParams.MATCH_PARENT;//宽高可设置具体大小
                lp.height = b;
                noticeDialog.getWindow().setAttributes(lp);
            }
            Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void refreshDataShopping() {
        Map<String, String> map = new ArrayMap<>();
        map.put("gameUserId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.GameUserId, "").toString());
        map.put("gameUserOs", "CROSS_AOS");
        map.put("applicationId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.applicationId, "").toString());
        new GCrossHttpUtils(new Gson().toJson(map), GCrossHttpConstant.getCrossShop).getCrossShop();
    }

    private void refreshDataIndex() {
        Map<String, String> map = new ArrayMap<>();
        map.put("gameUserId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.GameUserId, "").toString());
        map.put("gameUserOs", "CROSS_AOS");
        map.put("applicationId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.applicationId, "").toString());
        new GCrossHttpUtils(new Gson().toJson(map), GCrossHttpConstant.getCrossShop).getLoginGameUser();
    }

    //任务列表
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCrossGameMedia(EventList.getCrossGameMedia event) {
        CrossGameMediaBean crossGameMediaBean = event.crossGameMediaBean;
        questAdapter.setNewInstance(crossGameMediaBean.getResult());
    }

    //轮播图
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCrossBanners(EventList.getCrossBanners event) {
        resultBean = event.crossBannerBean.getResult();
        questBinding.tvGameName.setText(resultBean.getGameMediaName());//游戏名
        questBinding.tvName.setText(resultBean.getGameMediaName());
        Glide.with(requireActivity()).load(resultBean.getGameMediaIcon()).into(questBinding.ivGameImg);//游戏图片
        Glide.with(requireActivity()).load(resultBean.getGameMediaIcon()).into(questBinding.ivHeadImg);//游戏图片
        questBinding.tvIntroduce.setText(resultBean.getOther());
        questBinding.banner.addBannerLifecycleObserver(this)
                .setAdapter(new BannerViewAdapter(resultBean.getData(), getActivity()))
                //添加指示器
                .setIndicator(new CircleIndicator(getContext()));
        questBinding.banner.setOnBannerListener((data, position) -> startActivity(new Intent(getActivity(), WebViewActivity.class).putExtra("url", resultBean.getData().get(position).getBannersUrlAos())));
    }

    private void refreshData() {
        Map<String, String> map = new ArrayMap<>();
        map.put("gameUserId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.GameUserId, "").toString());
        map.put("gameUserOs", "CROSS_AOS");
//        map.put("gameMediaId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.GameMediaId, "").toString());
        new GCrossHttpUtils(new Gson().toJson(map), GCrossHttpConstant.getCrossGameMedia).getCrossGameMedia();
    }

}
