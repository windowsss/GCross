package com.cross.gcross.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.cross.gcross.adapter.GCrossShoppingAdapter;
import com.cross.gcross.base.GCrossBaseFragment;
import com.cross.gcross.bean.GCrossShoppingListBean;
import com.cross.gcross.dialog.WarmReminderDialog;
import com.cross.gcross.utils.EventList;
import com.cross.gcross.utils.GCrossHttpConstant;
import com.cross.gcross.utils.GCrossHttpUtils;
import com.cross.gcross.utils.GCrossSharedPreferencesUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商城
 */
public class GCrossShoppingFragment extends GCrossBaseFragment {
    private com.cross.gcross.databinding.FragmentShoppingGcrossBinding shoppingBinding;
    private List<String> stringList = new ArrayList<>();
    private GCrossShoppingAdapter shoppingAdapter;
    private Dialog noticeDialog;

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
            Map<String, String> map = new ArrayMap<>();
            map.put("gameUserId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.GameUserId, "").toString());
            map.put("gameUserOs", "CROSS_AOS");
            map.put("gameMediaId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.GameMediaId, "").toString());
            map.put("shopId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.ShopId, "").toString());
            new GCrossHttpUtils(new Gson().toJson(map), GCrossHttpConstant.updateShopUserDiamond).updateShopUserDiamond();
        });
        return shoppingBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            //注销事件
            EventBus.getDefault().unregister(this);
        }
    }


    //商城列表领取免费钻石
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateShopUserDiamond(EventList.updateShopUserDiamond event) {
        try {
            JSONObject jsonObject = new JSONObject(event.response);
            WarmReminderDialog.Builder warmPromptDialog = new WarmReminderDialog.Builder(getActivity());
            warmPromptDialog.setData(jsonObject.getString("result"));
            Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            refreshData();
            refreshDataIndex();
            noticeDialog = warmPromptDialog.create();
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void refreshDataIndex() {
        Map<String, String> map = new ArrayMap<>();
        map.put("gameUserId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.GameUserId, "").toString());
        map.put("gameUserOs", "CROSS_AOS");
        map.put("gameMediaId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.GameMediaId, "").toString());
        new GCrossHttpUtils(new Gson().toJson(map), GCrossHttpConstant.getLoginGameUser).getLoginGameUser();
    }


    private void refreshData() {
        Map<String, String> map = new ArrayMap<>();
        map.put("gameUserId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.GameUserId, "").toString());
        map.put("gameUserOs", "CROSS_AOS");
        map.put("gameMediaId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.GameMediaId, "").toString());
        map.put("shopId", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.ShopId, "").toString());
        new GCrossHttpUtils(new Gson().toJson(map), GCrossHttpConstant.getCrossShop).getCrossShop();
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
