package com.cross.gcross.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cross.gcross.GCrossSettingActivity;
import com.cross.gcross.R;
import com.cross.gcross.base.GCrossBaseFragment;
import com.cross.gcross.bean.GameUserBean;
import com.cross.gcross.databinding.FragmentIndexGcrossBinding;
import com.cross.gcross.utils.BrightnessUtils;
import com.cross.gcross.login.EventList;
import com.cross.gcross.utils.GCrossHttpConstant;
import com.cross.gcross.login.GCrossHttpUtils;
import com.cross.gcross.login.GCrossSharedPreferencesUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

/**
 * 首页
 */
public class GCrossIndexFragment extends GCrossBaseFragment {
    private FragmentIndexGcrossBinding indexBinding;
    private RefreshHandler refreshHandler;
    private GameUserBean.ResultBean resultBean;
    private boolean isFlag;
    private CallListener callListener;

    /**
     * 点击切换Fragment会调用
     */
    @Override
    protected void lazyLoad() {

    }

    public static GCrossIndexFragment newInstance() {
        return new GCrossIndexFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        indexBinding = FragmentIndexGcrossBinding.inflate(getLayoutInflater());
        if (!EventBus.getDefault().isRegistered(this)) {
            //注册事件
            EventBus.getDefault().register(this);
        }
        refreshHandler = new RefreshHandler();
        Map<String, String> map = new ArrayMap<>();
        map.put("gameUserId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.GameUserId, "").toString());
        map.put("gameUserOs", "CROSS_AOS");
        map.put("applicationId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.applicationId, "").toString());
        new GCrossHttpUtils(new Gson().toJson(map), GCrossHttpConstant.getLoginGameUser).getLoginGameUser();
        indexBinding.ivSetting.setOnClickListener(v -> startActivity(new Intent(getActivity(), GCrossSettingActivity.class)));
        indexBinding.rlBottom.setOnClickListener(v -> {
            if (callListener != null) {
                callListener.startQuest();
            }
        });
        return indexBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            //注销事件
            EventBus.getDefault().unregister(this);
        }
        if (refreshHandler != null) {
            refreshHandler.release();
        }

    }


    //查询用户信息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getGameUser(EventList.getGameUser event) {
        resultBean = event.gameUserBean.getResult();
//        Glide.with(requireActivity()).load(resultBean.getGameUserIcon()).into(indexBinding.ivHeadImg);//头像
        Glide.with(this)
                .load(resultBean.getGameUserIcon())
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(indexBinding.ivHeadImg);


        indexBinding.tvGameUserGrade.setText(new StringBuffer("LV.").append(resultBean.getGameUserGrade()));//等级
        indexBinding.tvGameUserDiamond.setText(resultBean.getGameUserDiamond());//宝石余额
        indexBinding.tvShopSumDiamond.setText(new StringBuffer(getResources().getString(R.string.at_most)).append(resultBean.getSumDiamond()).append(getResources().getString(R.string.gem_text)));
        if (!TextUtils.isEmpty(resultBean.getGameUserExperience())) {
            indexBinding.progressBar.setProgress(Integer.parseInt(resultBean.getGameUserExperience()));//经验
        }
        startRefreshUserList();
    }

    /**
     * 开始刷新定时送宝石
     */
    public void startRefreshUserList() {
        if (refreshHandler != null) {
            refreshHandler.removeMessages(RefreshHandler.WHAT_REFRESH);
            refreshHandler.sendEmptyMessageAtTime(RefreshHandler.WHAT_REFRESH, getNextTime(60000));
        }
    }

    private long getNextTime(int time) {
        long now = SystemClock.uptimeMillis();
        if (time < 1000) {
            return now + time;
        }
        return now + time + -now % 1000;
    }

    @SuppressLint("HandlerLeak")
    private class RefreshHandler extends Handler {
        private static final int WHAT_REFRESH = 1;

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == WHAT_REFRESH) {
                giveGemstone();
            }
        }

        public void release() {
            removeCallbacksAndMessages(null);
        }

    }

    //定义一个回调接口
    public interface CallListener {
        void startQuest();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CallListener) {
            callListener = (CallListener) context; // 2.2 获取到宿主activity并赋值
        } else {
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }
    }

    //赠送宝石
    public void giveGemstone() {
        int brightness = BrightnessUtils.getScreenBrightness(requireActivity());
        if (!isFlag) {
            isFlag = true;
            Glide.with(requireActivity()).load(R.mipmap.donghua).into(indexBinding.ivGif);
            BrightnessUtils.setScreenBrightness(requireActivity(), brightness + 100);
        } else {
            isFlag = false;
            Glide.with(requireActivity()).load(R.mipmap.icon_index_xuanzhuan).into(indexBinding.ivGif);
            if (brightness > 255) {
                BrightnessUtils.setScreenBrightness(requireActivity(), 255 - 100);
            } else {
                BrightnessUtils.setScreenBrightness(requireActivity(), brightness - 100);
            }

        }
        startRefreshUserList();
    }
}
