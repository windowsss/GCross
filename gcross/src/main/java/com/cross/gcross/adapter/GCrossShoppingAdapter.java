package com.cross.gcross.adapter;


import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cross.gcross.R;

import java.util.List;


/**
 * 商城列表
 */
public class GCrossShoppingAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        if (item != null) {
            helper.setText(R.id.tvNumber, item);
        }
    }

    public GCrossShoppingAdapter(@LayoutRes int layoutResId, @Nullable List<String> dataBeanList) {
        super(layoutResId, dataBeanList);
    }
}
