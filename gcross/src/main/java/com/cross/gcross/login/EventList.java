package com.cross.gcross.login;

import com.cross.gcross.bean.CrossBannerBean;
import com.cross.gcross.bean.CrossGameMediaBean;
import com.cross.gcross.bean.GCrossShoppingListBean;
import com.cross.gcross.bean.GameUserBean;
import com.cross.gcross.bean.LoginGameUserBean;

public class EventList {

    //商城列表
    public static class getCrossShop {
        public GCrossShoppingListBean shoppingListBean;

        getCrossShop(GCrossShoppingListBean shoppingListBean) {
            this.shoppingListBean = shoppingListBean;
        }
    }

    //任务列表
    public static class getCrossGameMedia {
        public CrossGameMediaBean crossGameMediaBean;

        getCrossGameMedia(CrossGameMediaBean crossGameMediaBean) {
            this.crossGameMediaBean = crossGameMediaBean;
        }
    }
    //轮番图列表接口
    public static class getCrossBanners {
        public CrossBannerBean crossBannerBean;

        getCrossBanners(CrossBannerBean crossBannerBean) {
            this.crossBannerBean = crossBannerBean;
        }
    }

    //查询用户信息接口
    public static class getGameUser {
        public GameUserBean gameUserBean;

        getGameUser(GameUserBean gameUserBean) {
            this.gameUserBean = gameUserBean;
        }
    }

//    //商城列表领取免费钻石
//    public static class updateShopUserDiamond {
//        public String response;
//
//        updateShopUserDiamond(String response) {
//            this.response = response;
//        }
//    }

    //任务列表领取钻石
    public static class saveCrossGameMediaDiamond {
        public String response;

        saveCrossGameMediaDiamond(String response) {
            this.response = response;
        }
    }
}
