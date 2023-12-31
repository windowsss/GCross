package com.cross.gcross.utils;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.cross.gcross.bean.CrossBannerBean;
import com.cross.gcross.bean.CrossGameMediaBean;
import com.cross.gcross.bean.GCrossShoppingListBean;
import com.cross.gcross.bean.GameUserBean;
import com.cross.gcross.bean.LoginGameUserBean;
import com.cross.gcross.login.GCrossSharedPreferencesUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GCrossHttpUtils {
    private final Call call;

    public GCrossHttpUtils(String json, String url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, json);
        String timeMillis = GCrossUtils.getCurrentTime();
        String md51 = new Md5().GetMD5Code(timeMillis + "gcross").substring(3);
        String md5 = GCrossUtils.getRandomString2(3) + md51.substring(0, md51.length() - 3) + GCrossUtils.getRandomString2(3);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("token", GCrossSharedPreferencesUtil.getData(GCrossSharedPreferencesUtil.TOKEN, "").toString())
                .header("sign", md5.toUpperCase())
                .header("timestamp", timeMillis)
                .build();
        call = okHttpClient.newCall(request);
    }

    //登录
    public void loginGameUser() {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                String responseBody ;
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseBody = Objects.requireNonNull(response.body()).string();
                if (response.code() == 200) {
                    LoginGameUserBean resultBean = new Gson().fromJson(responseBody, LoginGameUserBean.class);
                    if (resultBean == null || resultBean.getResult() == null || "0".equals(resultBean.getResult().getIsAuth())) {
                        //是否授权(1 授权 0 未授权)
                        GCrossSharedPreferencesUtil.putData(GCrossSharedPreferencesUtil.ISAUTH, "0");
                        return;
                    }
                    //是否授权(1 授权 0 未授权)
                    GCrossSharedPreferencesUtil.putData(GCrossSharedPreferencesUtil.ISAUTH, resultBean.getResult().getIsAuth());
                    GCrossSharedPreferencesUtil.putData(GCrossSharedPreferencesUtil.TOKEN, resultBean.getResult().getToken());
                    GCrossSharedPreferencesUtil.putData(GCrossSharedPreferencesUtil.GameUserId, resultBean.getResult().getUserId());
                    if (!TextUtils.isEmpty(resultBean.getResult().getGameMediaId())) {
                        GCrossSharedPreferencesUtil.putData(GCrossSharedPreferencesUtil.GameMediaId, resultBean.getResult().getGameMediaId());
                    }
                    GCrossSharedPreferencesUtil.putData(GCrossSharedPreferencesUtil.applicationId, resultBean.getResult().getApplicationId());
//                    EventBus.getDefault().post(new EventList.loginGameUser(new Gson().fromJson(responseBody, LoginGameUserBean.class)));
                } else {

                }
            }
        });
    }

//    //商城免费领取钻石接口
//    public void saveShopUserDiamond() {
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                String responseBody = Objects.requireNonNull(response.body()).string();
//                if (response.code() == 200) {
//                    EventBus.getDefault().post(new EventList.saveShopUserDiamond(responseBody));
//                }
//            }
//        });
//    }

    //商城列表
    public void getCrossShop() {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseBody = Objects.requireNonNull(response.body()).string();
                if (response.code() == 200) {
                    EventBus.getDefault().post(new EventList.getCrossShop(new Gson().fromJson(responseBody, GCrossShoppingListBean.class)));
                }
            }
        });
    }

    //轮播图列表接口
    public void getCrossBanners() {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseBody = Objects.requireNonNull(response.body()).string();
                if (response.code() == 200) {
                    EventBus.getDefault().post(new EventList.getCrossBanners(new Gson().fromJson(responseBody, CrossBannerBean.class)));
                }
            }
        });
    }

    //任务列表
    public void getCrossGameMedia() {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseBody = Objects.requireNonNull(response.body()).string();
                if (response.code() == 200) {
                    EventBus.getDefault().post(new EventList.getCrossGameMedia(new Gson().fromJson(responseBody, CrossGameMediaBean.class)));
                }
            }
        });
    }

    //任务列表领取宝石
    public void saveCrossGameMediaDiamond() {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseBody = Objects.requireNonNull(response.body()).string();
                if (response.code() == 200) {
                    EventBus.getDefault().post(new EventList.saveCrossGameMediaDiamond(responseBody));
                }
            }
        });
    }

    //获取用户信息
    public void getLoginGameUser() {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseBody = Objects.requireNonNull(response.body()).string();
                if (response.code() == 200) {
                    EventBus.getDefault().post(new EventList.getGameUser(new Gson().fromJson(responseBody, GameUserBean.class)));
                }
            }
        });
    }

//    //商城领取免费钻石
//    public void updateShopUserDiamond() {
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                String responseBody = Objects.requireNonNull(response.body()).string();
//                if (response.code() == 200) {
//                    EventBus.getDefault().post(new EventList.updateShopUserDiamond(responseBody));
//                }
//            }
//        });
//    }
}