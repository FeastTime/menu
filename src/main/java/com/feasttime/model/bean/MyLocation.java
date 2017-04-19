package com.feasttime.model.bean;

import java.util.List;

/**
 * Created by chen on 2017/4/19.
 */

public class MyLocation {
    String msg;
    boolean  success;
    List <LocationItem> obj;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<LocationItem> getObj() {
        return obj;
    }

    public void setObj(List<LocationItem> obj) {
        this.obj = obj;
    }
}
