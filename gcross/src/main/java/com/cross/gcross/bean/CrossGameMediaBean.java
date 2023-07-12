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
        private String gameMediaName;
        private String gameMediaType;
        private String gameMediaGoodsPrices;
        private String gameMediaPackage;
        private String gamMediaActivityUrl;
        private boolean receiveFlag;
        private String btnStatus;

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

        public String getGameMediaName() {
            return gameMediaName;
        }

        public void setGameMediaName(String gameMediaName) {
            this.gameMediaName = gameMediaName;
        }

        public String getGameMediaType() {
            return gameMediaType;
        }

        public void setGameMediaType(String gameMediaType) {
            this.gameMediaType = gameMediaType;
        }

        public String getGameMediaGoodsPrices() {
            return gameMediaGoodsPrices;
        }

        public void setGameMediaGoodsPrices(String gameMediaGoodsPrices) {
            this.gameMediaGoodsPrices = gameMediaGoodsPrices;
        }

        public String getGameMediaPackage() {
            return gameMediaPackage;
        }

        public void setGameMediaPackage(String gameMediaPackage) {
            this.gameMediaPackage = gameMediaPackage;
        }

        public String getGamMediaActivityUrl() {
            return gamMediaActivityUrl;
        }

        public void setGamMediaActivityUrl(String gamMediaActivityUrl) {
            this.gamMediaActivityUrl = gamMediaActivityUrl;
        }

        public boolean isReceiveFlag() {
            return receiveFlag;
        }

        public void setReceiveFlag(boolean receiveFlag) {
            this.receiveFlag = receiveFlag;
        }
    }
}
