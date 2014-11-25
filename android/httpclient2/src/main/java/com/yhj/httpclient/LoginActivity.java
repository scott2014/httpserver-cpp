package com.yhj.httpclient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yhj.httpclient.model.BaseResponse;
import com.yhj.httpclient.model.HttpListener;
import com.yhj.httpclient.model.HttpTask;
import com.yhj.httpclient.model.UrlConst;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class LoginActivity extends BaseActivity {

    private HttpTask task = new HttpTask(this);

    private EditText mUsernameEdit;
    private EditText mPasswdEdit;
    private Button mLoginBtn;
    private TextView mRegText;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mUsernameEdit = (EditText) this.findViewById(R.id.et_username);
        this.mPasswdEdit = (EditText) this.findViewById(R.id.et_passwd);
        this.mLoginBtn = (Button) this.findViewById(R.id.btn_login);
        this.mRegText = (TextView) this.findViewById(R.id.tv_reg);


        this.mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = showProgressDialog();
                HttpPost post = new HttpPost(UrlConst.URL_LOGIN);

                String username = mUsernameEdit.getText().toString();
                String passwd = mPasswdEdit.getText().toString();

                JSONObject json = new JSONObject();
                try {
                    json.put("username",username);
                    json.put("passwd",passwd);
                    post.setEntity(new StringEntity(json.toString(),"UTF-8"));
                    task.execute(post);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });

        mRegText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
            Object data = response.getData();
            Log.i("data", String.valueOf(data));
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
            showDialog("登录","用户名或者密码错误");
        }
        if ("2".equals(response.getCode())) {
            showDialog("登录","服务异常");
        }
    }

    @Override
    public void onHttpErr(String code, String msg) {
        super.onHttpErr(code, msg);
        dialog.dismiss();
        showDialog("登录", "连接异常");
    }
}
