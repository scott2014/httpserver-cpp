package com.yhj.httpclient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yhj.httpclient.model.BaseResponse;
import com.yhj.httpclient.model.HttpListener;
import com.yhj.httpclient.model.HttpTask;
import com.yhj.httpclient.model.UrlConst;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class RegisterActivity extends BaseActivity {
    private EditText mUsernameEdit;
    private EditText mPasswdEdit;
    private Button mRegBtn;
    private ProgressDialog dialog;

    private HttpTask task = new HttpTask(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.mUsernameEdit = (EditText) this.findViewById(R.id.et_username);
        this.mPasswdEdit = (EditText) this.findViewById(R.id.et_passwd);

        this.mRegBtn = (Button) this.findViewById(R.id.btn_register);

        this.mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = showProgressDialog();
                HttpPost post = new HttpPost(UrlConst.URL_REGISTER);

                JSONObject json = new JSONObject();
                try {
                    json.put("username",mUsernameEdit.getText().toString());
                    json.put("passwd",mPasswdEdit.getText().toString());
                    post.setEntity(new StringEntity(json.toString(),"UTF-8"));
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                task.execute(post);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onHttpOk(BaseResponse response) {
        super.onHttpOk(response);
        dialog.dismiss();
        if ("0".equals(response.getCode())) {
            showDialog("注册结果","注册成功");
            Object data = response.getData();
            try {
                JSONObject json = new JSONObject(String.valueOf(data));
                String userid = String.valueOf(json.get("userid"));
                putSharePref("userid",userid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            go(MsgActivity.class);
            finish();
        }
        if ("1".equals(response.getCode())) {
            showDialog("注册结果","用户已存在");
        }
        if ("2".equals(response.getCode())) {
            showDialog("注册结果","调用数据库失败");
        }
    }

    @Override
    public void onHttpErr(String code, String msg) {
        super.onHttpErr(code,msg);
        showDialog("注册结果","请求超时");
    }
}
