package com.yhj.httpclient.model;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by scott on 14-11-22.
 */
public class HttpTask {
    private HttpListener listener;
    private boolean isActive = true;
    private Handler handler  = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HTTP_OK:
                    BaseResponse resp = (BaseResponse) msg.getData().getSerializable("resp");
                    listener.onHttpOk(resp);
                    break;
                case HTTP_ERR:
                    listener.onHttpErr(msg.getData().getString("code"),msg.getData().getString("msg"));
                    break;
            }
        }
    };

    private final int HTTP_OK = 1;
    private final int HTTP_ERR = 2;

    public HttpTask(HttpListener listener) {
        this.listener = listener;
    }

    public void execute(final HttpPost post) {
        if (isActive) {
            new Thread() {
                @Override
                public void run() {
                    isActive = false;
                    HttpClient client = new DefaultHttpClient();
                    try {
                        HttpResponse response = client.execute(post);
                        InputStream is = response.getEntity().getContent();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        StringBuilder builder = new StringBuilder("");
                        String str = null;
                        while ((str = br.readLine()) != null) {
                            builder.append(str);
                        }
                        br.close();
                        is.close();
                        Message msg = new Message();
                        msg.what = HTTP_OK;

                        Gson gson = new Gson();
                        BaseResponse resp = gson.fromJson(builder.toString(),BaseResponse.class);
                        Bundle data = new Bundle();
                        data.putSerializable("resp",resp);
                        msg.setData(data);
                        handler.sendMessage(msg);
                        Log.i("gson",builder.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Message msg = new Message();
                        msg.what = HTTP_ERR;
                        Bundle data = new Bundle();
                        data.putString("code", "-1");
                        data.putString("msg", e.getMessage());
                        msg.setData(data);
                        handler.sendMessage(msg);
                    }
                    isActive = true;
                }
            }.start();
        }
    }
}
