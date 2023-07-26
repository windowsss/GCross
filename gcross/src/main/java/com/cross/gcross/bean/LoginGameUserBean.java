package com.cross.gcross.bean;

public class LoginGameUserBean {

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
        private String status;
        private String token;
        private String userId;
        private String gameMediaId;
        private String applicationId;
        private String gameUserDiamond;
        private String isAuth;

        public String getIsAuth() {
            return isAuth;
        }

        public void setIsAuth(String isAuth) {
            this.isAuth = isAuth;
        }

        public String getGameUserDiamond() {
            return gameUserDiamond;
        }

        public void setGameUserDiamond(String gameUserDiamond) {
            this.gameUserDiamond = gameUserDiamond;
        }

        public String getApplicationId() {
            return applicationId;
        }

        public void setApplicationId(String applicationId) {
            this.applicationId = applicationId;
        }

        public String getGameMediaId() {
            return gameMediaId;
        }

        public void setGameMediaId(String gameMediaId) {
            this.gameMediaId = gameMediaId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
