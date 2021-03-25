package com.lsh.birthday.entry;

import java.io.Serializable;

public class Common implements Serializable {
    
    private int coId;
    private String coKey;
    private String coValue;
    private int coEnabled;

    public int getCoId() {
        return coId;
    }

    public void setCoId(int coId) {
        this.coId = coId;
    }

    public String getCoKey() {
        return coKey;
    }

    public void setCoKey(String coKey) {
        this.coKey = coKey;
    }

    public String getCoValue() {
        return coValue;
    }

    public void setCoValue(String coValue) {
        this.coValue = coValue;
    }

    public int getCoEnabled() {
        return coEnabled;
    }

    public void setCoEnabled(int coEnabled) {
        this.coEnabled = coEnabled;
    }
}
