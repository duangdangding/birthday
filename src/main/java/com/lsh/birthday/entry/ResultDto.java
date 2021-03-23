package com.lsh.birthday.entry;

public class ResultDto {
    private int state;
    private Object result;
    private String message;
    
    public ResultDto(int state,Object obj) {
        this.state = state;
        this.result = obj;
    }

    public static ResultDto success(Object obj) {
        return new ResultDto(200,obj);
    }

    public int getState() {
        return state;
    }
    
    public void setState(int state) {
        this.state = state;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
