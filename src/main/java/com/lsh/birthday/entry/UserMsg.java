package com.lsh.birthday.entry;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserMsg implements Serializable {
    
    private int userId;
    private String userName;
    private String userIp;
    private Timestamp loginTime;
    private int loginSum;
    private String userAddress;
    private int userBg = 1;
    private String userXz;

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
        return loginSum;
    }

    public void setLoginSum(int loginSum) {
        this.loginSum = loginSum;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getUserBg() {
        return userBg;
    }

    public void setUserBg(int userBg) {
        this.userBg = userBg;
    }

    public String getUserXz() {
        return userXz;
    }

    public void setUserXz(String userXz) {
        this.userXz = userXz;
    }
}
