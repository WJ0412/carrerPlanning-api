package com.mars.vo;

import lombok.Data;

@Data
public class ResponseData {
    private int code;
    private Object data;
    private String msg;

    public ResponseData() {  //无参构造方法
    }

    public ResponseData(int code, Object data) {  //有参构造方法
        this.code = code;
        this.data = data;
    }

    public ResponseData(int code, Object data, String msg) {  //有参构造方法
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ResponseData buildSuccess(Object data) {  //成功
        return new ResponseData(0, data);
    }

    public static ResponseData buildError(String msg) {  //失败
        return new ResponseData(-1, null, msg);
    }

    public static ResponseData buildError(int code, String msg) {  //失败
        return new ResponseData(code, "", msg);
    }
}
