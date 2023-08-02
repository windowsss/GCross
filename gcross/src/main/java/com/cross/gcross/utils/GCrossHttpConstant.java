package com.cross.gcross.utils;

import com.cross.gcross.GCrossConstants;

public class GCrossHttpConstant {


    public static final String LOGIN_GAME_USER = GCrossConstants.API_BASIC_URL_PHP + "loginGameUser";//登录
    public static final String getLoginGameUser = GCrossConstants.API_BASIC_URL_PHP + "getLoginGameUser";//查询用户信息接口
    public static final String getCrossShop = GCrossConstants.API_BASIC_URL_PHP + "getCrossShop";//商城列表接口
    public static final String getCrossGameMedia = GCrossConstants.API_BASIC_URL_PHP + "getCrossApplication";//获取游戏列表接口
    public static final String saveCrossGameMediaDiamond = GCrossConstants.API_BASIC_URL_PHP + "saveCrossApplicationDiamond";//游戏领取钻石接口
    public static final String getCrossBanners = GCrossConstants.API_BASIC_URL_PHP + "getCrossBanners";//轮番图列表接口
}
