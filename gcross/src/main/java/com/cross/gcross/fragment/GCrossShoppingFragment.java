package com.cross.gcross.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.cross.gcross.R;
import com.cross.gcross.adapter.GCrossShoppingAdapter;
import com.cross.gcross.base.GCrossBaseFragment;
import com.cross.gcross.bean.GCrossShoppingListBean;
import com.cross.gcross.login.EventList;
import com.cross.gcross.utils.GCrossHttpConstant;
import com.cross.gcross.login.GCrossHttpUtils;
import com.cross.gcross.utils.GCrossSharedPreferencesUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商城
 */
public class GCrossShoppingFragment extends GCrossBaseFragment {
    private com.cross.gcross.databinding.FragmentShoppingGcrossBinding shoppingBinding;
    private final List<String> stringList = new ArrayList<>();
    private GCrossShoppingAdapter shoppingAdapter;
    private CallShoppingListener callListener;

    /**
     * 点击切换Fragment会调用
     */
    @Override
    protected void lazyLoad() {

    }

    public static GCrossShoppingFragment newInstance() {
        return new GCrossShoppingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        shoppingBinding = com.cross.gcross.databinding.FragmentShoppingGcrossBinding.inflate(getLayoutInflater());
        if (!EventBus.getDefault().isRegistered(this)) {
            //注册事件
            EventBus.getDefault().register(this);
        }
        shoppingAdapter = new GCrossShoppingAdapter(R.layout.adapter_shopping_gcross, stringList);
        shoppingBinding.rlList.setAdapter(shoppingAdapter);
        shoppingAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (callListener != null) {
                callListener.startQuestShopping();
            }
        });
        refreshDataShopping();
        return shoppingBinding.getRoot();
    }

    private void refreshDataShopping() {
        Map<String, String> map = new ArrayMap<>();
        map.put("gameUserId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.GameUserId, "").toString());
        map.put("gameUserOs", "CROSS_AOS");
        map.put("applicationId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.applicationId, "").toString());
        new GCrossHttpUtils(new Gson().toJson(map), GCrossHttpConstant.getCrossShop).getCrossShop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            //注销事件
            EventBus.getDefault().unregister(this);
        }
    }

    //定义一个回调接口
    public interface CallShoppingListener {
        void startQuestShopping();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CallShoppingListener) {
            callListener = (CallShoppingListener) context; // 2.2 获取到宿主activity并赋值
        } else {
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    //商城列表
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCrossShop(EventList.getCrossShop event) {
        GCrossShoppingListBean.ResultBean resultBean = event.shoppingListBean.getResult();
        shoppingAdapter.setNewInstance(resultBean.getDiamond());
        Glide.with(requireActivity()).load(resultBean.getGameUserIcon()).into(shoppingBinding.ivHeadImg);//头像
        shoppingBinding.tvGameUserGrade.setText(new StringBuffer("LV.").append(resultBean.getGameUserGrade()));//等级
        shoppingBinding.tvGameUserDiamond.setText(resultBean.getGameUserDiamond());//宝石余额
        if (!TextUtils.isEmpty(resultBean.getGameUserExperience())) {
            shoppingBinding.progressBar.setProgress(Integer.parseInt(resultBean.getGameUserExperience()));//经验
        }
    }
}