package com.example.juandie_hua.model;

public class Resp<T> {

    /**
     * status : 1
     * msg :
     * data : {}
     * PHPSESSID : c389cfa50cb438ca0479c87d2c9566d8
     * uid : aHpqMkZkMXZyMzAzZ0FOTjVPNnpQQT09
     */

    private String status;
    private String msg;
    private T data;
    private String PHPSESSID;
    private String uid;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getPHPSESSID() {
        return PHPSESSID;
    }

    public void setPHPSESSID(String PHPSESSID) {
        this.PHPSESSID = PHPSESSID;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
