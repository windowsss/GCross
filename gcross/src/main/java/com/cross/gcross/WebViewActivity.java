package com.cross.gcross;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;

import com.cross.gcross.databinding.ActivityWebBinding;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.cross.gcross.databinding.ActivityWebBinding binding = ActivityWebBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String type = getIntent().getStringExtra("type");
        if (TextUtils.isEmpty(type)) {
            binding.webView.loadUrl(getIntent().getStringExtra("url"));
        } else {
            binding.webView.loadData(getIntent().getStringExtra("text"), "text/html", "utf-8");
        }

    }
}