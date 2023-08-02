package com.cross.gcross.bean;

import java.util.List;

public class CrossGameMediaBean {

    private boolean success;
    private String message;
    private String code;
    private List<ResultBean> result;
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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static class ResultBean {
        private String gameMediaId;
        private String gameMediaIcon;
        private String gameMediaNameKo;
        private String gameMediaNameEn;
        private String gameMediaTypeKo;
        private String gameMediaTypeEn;
        private String gameMediaGoodsPrices;
        private String gameMediaPackageAos;
        private String gameMediaPackageIos;
        private String gamMediaActivityUrlIos;
        private String gamMediaActivityUrlAos;
        private boolean receiveFlag;
        private String applicationId;
        private String activityId;
        private String activityName;
        private String btnStatus;

        public String getApplicationId() {
            return applicationId;
        }

        public void setApplicationId(String applicationId) {
            this.applicationId = applicationId;
        }

        public String getBtnStatus() {
            return btnStatus;
        }

        public void setBtnStatus(String btnStatus) {
            this.btnStatus = btnStatus;
        }

        public String getGameMediaId() {
            return gameMediaId;
        }

        public void setGameMediaId(String gameMediaId) {
            this.gameMediaId = gameMediaId;
        }

        public String getGameMediaIcon() {
            return gameMediaIcon;
        }

        public void setGameMediaIcon(String gameMediaIcon) {
            this.gameMediaIcon = gameMediaIcon;
        }

        public String getGameMediaNameKo() {
            return gameMediaNameKo;
        }

        public void setGameMediaNameKo(String gameMediaNameKo) {
            this.gameMediaNameKo = gameMediaNameKo;
        }

        public String getGameMediaNameEn() {
            return gameMediaNameEn;
        }

        public void setGameMediaNameEn(String gameMediaNameEn) {
            this.gameMediaNameEn = gameMediaNameEn;
        }

        public String getGameMediaTypeKo() {
            return gameMediaTypeKo;
        }

        public void setGameMediaTypeKo(String gameMediaTypeKo) {
            this.gameMediaTypeKo = gameMediaTypeKo;
        }

        public String getGameMediaTypeEn() {
            return gameMediaTypeEn;
        }

        public void setGameMediaTypeEn(String gameMediaTypeEn) {
            this.gameMediaTypeEn = gameMediaTypeEn;
        }

        public String getGameMediaGoodsPrices() {
            return gameMediaGoodsPrices;
        }

        public void setGameMediaGoodsPrices(String gameMediaGoodsPrices) {
            this.gameMediaGoodsPrices = gameMediaGoodsPrices;
        }

        public String getGameMediaPackageAos() {
            return gameMediaPackageAos;
        }

        public void setGameMediaPackageAos(String gameMediaPackageAos) {
            this.gameMediaPackageAos = gameMediaPackageAos;
        }

        public String getGameMediaPackageIos() {
            return gameMediaPackageIos;
        }

        public void setGameMediaPackageIos(String gameMediaPackageIos) {
            this.gameMediaPackageIos = gameMediaPackageIos;
        }

        public String getGamMediaActivityUrlIos() {
            return gamMediaActivityUrlIos;
        }

        public void setGamMediaActivityUrlIos(String gamMediaActivityUrlIos) {
            this.gamMediaActivityUrlIos = gamMediaActivityUrlIos;
        }

        public String getGamMediaActivityUrlAos() {
            return gamMediaActivityUrlAos;
        }

        public void setGamMediaActivityUrlAos(String gamMediaActivityUrlAos) {
            this.gamMediaActivityUrlAos = gamMediaActivityUrlAos;
        }

        public boolean isReceiveFlag() {
            return receiveFlag;
        }

        public void setReceiveFlag(boolean receiveFlag) {
            this.receiveFlag = receiveFlag;
        }

        public String getActivityId() {
            return activityId;
        }

        public void setActivityId(String activityId) {
            this.activityId = activityId;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }
    }
}
