package com.cross.gcross.bean;

import java.util.List;

public class CrossBannerBean {

    private boolean success;
    private String message;
    private int code;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
        private List<DataBean> data;
        private String clauseContent;
        private String privacyPolicyContent;
        private String gameMediaName;
        private String gameMediaIcon;
        private String other;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public String getClauseContent() {
            return clauseContent;
        }

        public void setClauseContent(String clauseContent) {
            this.clauseContent = clauseContent;
        }

        public String getPrivacyPolicyContent() {
            return privacyPolicyContent;
        }

        public void setPrivacyPolicyContent(String privacyPolicyContent) {
            this.privacyPolicyContent = privacyPolicyContent;
        }

        public String getGameMediaName() {
            return gameMediaName;
        }

        public void setGameMediaName(String gameMediaName) {
            this.gameMediaName = gameMediaName;
        }

        public String getGameMediaIcon() {
            return gameMediaIcon;
        }

        public void setGameMediaIcon(String gameMediaIcon) {
            this.gameMediaIcon = gameMediaIcon;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public static class DataBean {
            private String bannersUrlAos;
            private String bannersUrlIos;
            private String bannersImgKo;
            private String bannersImgEn;

            public String getBannersUrlAos() {
                return bannersUrlAos;
            }

            public void setBannersUrlAos(String bannersUrlAos) {
                this.bannersUrlAos = bannersUrlAos;
            }

            public String getBannersUrlIos() {
                return bannersUrlIos;
            }

            public void setBannersUrlIos(String bannersUrlIos) {
                this.bannersUrlIos = bannersUrlIos;
            }

            public String getBannersImgKo() {
                return bannersImgKo;
            }

            public void setBannersImgKo(String bannersImgKo) {
                this.bannersImgKo = bannersImgKo;
            }

            public String getBannersImgEn() {
                return bannersImgEn;
            }

            public void setBannersImgEn(String bannersImgEn) {
                this.bannersImgEn = bannersImgEn;
            }
        }
    }
}
