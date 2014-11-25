package com.yhj.httpclient.model;

/**
 * Created by scott on 14-11-22.
 */
public interface HttpListener {
    public void onHttpOk(BaseResponse response);
    public void onHttpErr(String code,String msg);
}
