package com.cross.gcross.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cross.gcross.R;

/**
 * 用户协议dialog
 */
public class WarmReminderDialog extends Dialog {

    public WarmReminderDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private String number;
        private final Context mContext;

        public WarmReminderDialog dialog;

        public void setData(String number) {
            this.number = number;
        }

        public Builder(Context context) {
            this.mContext = context;
        }

        public void closeDialog() {
            dialog.dismiss();
        }


        public WarmReminderDialog create() {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            dialog = new WarmReminderDialog(mContext, R.style.Dialog);
            dialog.setCancelable(false);
            View layout = inflater.inflate(R.layout.dialog_warm_reminder, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layout.findViewById(R.id.ivClose).setOnClickListener(v -> dialog.dismiss());
            layout.findViewById(R.id.ivCloseDialog).setOnClickListener(v -> dialog.dismiss());
            TextView tvContent = layout.findViewById(R.id.tvContent);
            tvContent.setText(new StringBuffer(number).append(mContext.getResources().getString(R.string.gem)));
            dialog.setContentView(layout);
            dialog.setCancelable(false);
            return dialog;
        }
    }
}