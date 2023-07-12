package com.cross.gcross.bean;

public class GameUserBean {


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
        private String gameUserId;
        private String gameUserName;
        private String gameUserIcon;
        private String gameUserGrade;
        private String gameUserDiamond;
        private String gameUserExperience;
        private String shopSumDiamond;
        private String shopDiamond;
        private String shopTime;
        private String shopId;
        private String gameMediaId;

        public String getGameUserId() {
            return gameUserId;
        }

        public void setGameUserId(String gameUserId) {
            this.gameUserId = gameUserId;
        }

        public String getGameUserName() {
            return gameUserName;
        }

        public void setGameUserName(String gameUserName) {
            this.gameUserName = gameUserName;
        }

        public String getGameUserIcon() {
            return gameUserIcon;
        }

        public void setGameUserIcon(String gameUserIcon) {
            this.gameUserIcon = gameUserIcon;
        }

        public String getGameUserGrade() {
            return gameUserGrade;
        }

        public void setGameUserGrade(String gameUserGrade) {
            this.gameUserGrade = gameUserGrade;
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

        public String getShopSumDiamond() {
            return shopSumDiamond;
        }

        public void setShopSumDiamond(String shopSumDiamond) {
            this.shopSumDiamond = shopSumDiamond;
        }

        public String getShopDiamond() {
            return shopDiamond;
        }

        public void setShopDiamond(String shopDiamond) {
            this.shopDiamond = shopDiamond;
        }

        public String getShopTime() {
            return shopTime;
        }

        public void setShopTime(String shopTime) {
            this.shopTime = shopTime;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getGameMediaId() {
            return gameMediaId;
        }

        public void setGameMediaId(String gameMediaId) {
            this.gameMediaId = gameMediaId;
        }
    }
}
