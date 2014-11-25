package com.yhj.httpclient.model;

import java.io.Serializable;

/**
 * Created by scott on 14-11-22.
 */
public class BaseResponse implements Serializable {
    private String code;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
