package com.jjw.request;

public class TestRequest {

    private String userId;

    private String userName;

    private TestContent content;

    public TestRequest() {
    }

    public TestRequest(String userId, String userName,TestContent content) {
        this.userId = userId;
        this.userName = userName;
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public TestContent getContent() {
        return content;
    }

    public void setContent(TestContent content) {
        this.content = content;
    }

    public static class TestContent{

        private String phone;

        public TestContent() {
        }

        public TestContent(String phone) {
            this.phone = phone;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

    }

}
