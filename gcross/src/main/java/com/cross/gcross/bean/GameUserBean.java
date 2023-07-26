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
        private String sumDiamond;
        private String applicationId;

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

        public String getSumDiamond() {
            return sumDiamond;
        }

        public void setSumDiamond(String sumDiamond) {
            this.sumDiamond = sumDiamond;
        }

        public String getApplicationId() {
            return applicationId;
        }

        public void setApplicationId(String applicationId) {
            this.applicationId = applicationId;
        }
    }
}
