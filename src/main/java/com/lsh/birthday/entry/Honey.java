package com.lsh.birthday.entry;

import java.io.Serializable;

public class Honey implements Serializable {
    private int hId;
    private String hName;
    
    public Honey(int hId,String hName) {
        this.hId = hId;
        this.hName = hName;
    }

    public int gethId() {
        return hId;
    }

    public void sethId(int hId) {
        this.hId = hId;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }
}
