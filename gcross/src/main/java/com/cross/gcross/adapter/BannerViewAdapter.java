package com.cross.gcross.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cross.gcross.bean.CrossBannerBean;
import com.cross.gcross.login.GCrossSharedPreferencesUtil;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class BannerViewAdapter extends BannerAdapter<CrossBannerBean.ResultBean.DataBean, BannerViewAdapter.BannerViewHodler> {

    public BannerViewAdapter(List<CrossBannerBean.ResultBean.DataBean> banners, Context context) {
        super(banners);
    }

    @Override
    public BannerViewHodler onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        //setScaleType对图片进行大小处理 CENTER_CROP对原图居中显示后进行等比放缩处理，使最小边等于ImageView的相应边
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        return new BannerViewHodler(imageView);

    }

    //用于绑定图片资源文件
    @Override
    public void onBindView(BannerViewHodler holder, CrossBannerBean.ResultBean.DataBean data, int position, int size) {
        if ("korean".equals(GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.Language, "").toString())) {
            Glide.with(holder.imageView).load(data.getBannersImgKo()).into(holder.imageView);
        } else {
            Glide.with(holder.imageView).load(data.getBannersImgEn()).into(holder.imageView);
        }
    }

    //ViewHolder主要用于容纳view视图
    public static class BannerViewHodler extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHodler(@NonNull ImageView itemView) {
            super(itemView);
            this.imageView = itemView;
        }
    }
}