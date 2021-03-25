package com.lsh.birthday.entry;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserMsg implements Serializable {
    
    private int userId;
    private String userName;
    private String userIp;
    private Timestamp loginTime;
    private int LoginSum;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public int getLoginSum() {
        return LoginSum;
    }

    public void setLoginSum(int loginSum) {
        LoginSum = loginSum;
    }
}
