package com.cross.gcross.bean;

import java.util.List;

public class GCrossShoppingListBean {

    private boolean success;
    private String message;
    private String code;
    private ResultBean result;
    private long timestamp;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static class ResultBean {
        private String gameUserPk;
        private String gameUserGrade;
        private String gameUserIcon;
        private String userName;
        private String gameUserDiamond;
        private String gameUserExperience;
        private String shopId;
        private List<String> diamond;

        public String getGameUserPk() {
            return gameUserPk;
        }

        public void setGameUserPk(String gameUserPk) {
            this.gameUserPk = gameUserPk;
        }

        public String getGameUserGrade() {
            return gameUserGrade;
        }

        public void setGameUserGrade(String gameUserGrade) {
            this.gameUserGrade = gameUserGrade;
        }

        public String getGameUserIcon() {
            return gameUserIcon;
        }

        public void setGameUserIcon(String gameUserIcon) {
            this.gameUserIcon = gameUserIcon;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getGameUserDiamond() {
            return gameUserDiamond;
        }

        public void setGameUserDiamond(String gameUserDiamond) {
            this.gameUserDiamond = gameUserDiamond;
        }

        public String getGameUserExperience() {
            return gameUserExperience;
        }

        public void setGameUserExperience(String gameUserExperience) {
            this.gameUserExperience = gameUserExperience;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public List<String> getDiamond() {
            return diamond;
        }

        public void setDiamond(List<String> diamond) {
            this.diamond = diamond;
        }
    }
}
