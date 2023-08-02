package com.cross.gcross.adapter;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cross.gcross.R;
import com.cross.gcross.bean.CrossGameMediaBean;
import com.cross.gcross.login.GCrossSharedPreferencesUtil;

import java.util.List;


/**
 * 任务列表
 */
public class QuestAdapter extends BaseQuickAdapter<CrossGameMediaBean.ResultBean, BaseViewHolder> {

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CrossGameMediaBean.ResultBean item) {
        if (item != null) {
            if ("korean".equals(GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.Language, "").toString())) {
                helper.setText(R.id.tvName, item.getGameMediaNameKo());
                helper.setText(R.id.tvGameMediaType, item.getGameMediaTypeKo());
            } else {
                helper.setText(R.id.tvName, item.getGameMediaNameEn());
                helper.setText(R.id.tvGameMediaType, item.getGameMediaTypeEn());
            }


            ImageView ivHeadImg = helper.getView(R.id.ivHeadImg);
            Glide.with(getContext()).load(item.getGameMediaIcon()).into(ivHeadImg);
            helper.setText(R.id.tvNumber, item.getGameMediaGoodsPrices());
            boolean isInstall;
//            PackageManager packageManager = getContext().getPackageManager();
//            try {
//                PackageInfo packageInfo = packageManager.getPackageInfo(item.getGameMediaPackageAos(), 0);
//                // 应用已安装
//                isInstall = true;
//            } catch (PackageManager.NameNotFoundException e) {
//                // 应用未安装
//                isInstall = false;
//            }
            isInstall = isInstall(getContext(), item.getGameMediaPackageAos());
            //如果安装了应用 并且也领取奖励了 显示玩游戏
            if (isInstall && item.isReceiveFlag()) {
                item.setBtnStatus("1");
                helper.setText(R.id.tvBtnStatus, getContext().getResources().getString(R.string.play_game));
                helper.setImageResource(R.id.ivBackGround, R.mipmap.btn_black);
                //如果安装了应用但是没领取奖励 显示领取奖励
            } else if (isInstall && !item.isReceiveFlag()) {
                item.setBtnStatus("2");
                helper.setText(R.id.tvBtnStatus, getContext().getResources().getString(R.string.get_rewards));
                helper.setImageResource(R.id.ivBackGround, R.mipmap.btn_green);
            } else {
                item.setBtnStatus("3");
                helper.setText(R.id.tvBtnStatus, getContext().getResources().getString(R.string.go_get));
                helper.setImageResource(R.id.ivBackGround, R.mipmap.btn_blue);
            }
        }
    }

    private boolean isInstall(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            // 循环判断是否存在指定包名
            if (pinfo.get(i).packageName.equalsIgnoreCase(packageName)) {
                return true;
            }
        }
        return false;
    }

    public QuestAdapter(@LayoutRes int layoutResId, @Nullable List<CrossGameMediaBean.ResultBean> dataBeanList) {
        super(layoutResId, dataBeanList);
    }

}
