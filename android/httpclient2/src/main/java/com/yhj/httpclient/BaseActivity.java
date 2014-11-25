package com.yhj.httpclient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import com.yhj.httpclient.model.BaseResponse;
import com.yhj.httpclient.model.HttpListener;

/**
 * Created by scott on 14-11-24.
 */
public class BaseActivity extends Activity implements HttpListener {

    @Override
    public void onHttpOk(BaseResponse response) {

    }

    @Override
    public void onHttpErr(String code, String msg) {

    }

    protected ProgressDialog showProgressDialog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    protected Dialog showDialog(String title,String msg) {
        Dialog dialog = new AlertDialog.Builder(this).setTitle(title).setMessage(msg)
                .setCancelable(true).create();
        dialog.show();
        return dialog;
    }

    protected void go(Class cls) {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }

    protected void putSharePref(String key,String value) {
        SharedPreferences pref = getSharedPreferences("httpclient", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key,value);
        editor.commit();
    }

    protected String getSharePref(String key) {
        SharedPreferences pref = getSharedPreferences("httpclient", Context.MODE_PRIVATE);
        return pref.getString(key,"");
    }

    protected void showYesNoDialog(String title,String msg,DialogInterface.OnClickListener clickListener) {
        Dialog dialog = new AlertDialog.Builder(this)
                            .setPositiveButton("否",clickListener)
                            .setNegativeButton("是",clickListener)
                            .setTitle(title)
                            .setMessage(msg)
                            .setCancelable(false)
                            .create();
        dialog.show();
    }
}
