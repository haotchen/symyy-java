package com.haotchen.syyimei.common;

import lombok.Data;
@Data
public class R {
    private int code;
    private String msg;
    private Object data;
    private String timeStamp;

    public static R ok(String msg,Object data){
        R r = new R();
        r.setCode(200);
        r.setMsg(msg);
        r.setData(data);
        r.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        return r;
    }
    public static R fail(String msg,int errorCode){
        R r = new R();
        r.setCode(errorCode);
        r.setMsg(msg);
        r.setData(null);
        r.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        return r;
    }

}
