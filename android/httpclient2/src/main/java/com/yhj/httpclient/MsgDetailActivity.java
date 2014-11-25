package com.yhj.httpclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yhj.httpclient.model.BaseResponse;
import com.yhj.httpclient.model.HttpListener;
import com.yhj.httpclient.model.HttpTask;
import com.yhj.httpclient.model.Msg;
import com.yhj.httpclient.model.UrlConst;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;


public class MsgDetailActivity extends BaseActivity {
    private EditText mTitleText;
    private EditText mContentText;

    private Button mUpdateBtn;

    private String msgid;

    private HttpTask task = new HttpTask(this);

    private HttpTask updateTask = new HttpTask(new HttpListener() {
        @Override
        public void onHttpOk(BaseResponse response) {
            if ("0".equals(response.getCode())) {
                finish();
            } else {
                showDialog("更新结果","更新失败");
            }
        }

        @Override
        public void onHttpErr(String code, String msg) {
            showDialog("更新结果","网络连接失败");
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_detail);

        this.mTitleText = (EditText) this.findViewById(R.id.tv_title);
        this.mContentText = (EditText) this.findViewById(R.id.tv_content);

        this.mUpdateBtn = (Button) this.findViewById(R.id.btn_update);

        this.msgid = getIntent().getStringExtra("msgid");

        HttpPost post = new HttpPost(UrlConst.URL_MSG_DETAIL);
        JSONObject json = new JSONObject();
        try {
            json.put("msgid",msgid);
            Log.e("json", json.toString());
            post.setEntity(new StringEntity(json.toString(),"UTF-8"));
            task.execute(post);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        this.mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpPost post = new HttpPost(UrlConst.URL_MSG_UPDATE);
                JSONObject json = new JSONObject();
                try {
                    json.put("msgid",msgid);
                    json.put("title",mTitleText.getText().toString());
                    json.put("content",mContentText.getText().toString());
                    post.setEntity(new StringEntity(json.toString(),"UTF-8"));
                    updateTask.execute(post);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_msg_detail, menu);
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

        if ("0".equals(response.getCode())) {
            Msg msg = new Gson().fromJson(String.valueOf(response.getData()),Msg.class);

            mTitleText.setText(msg.getTitle());
            mContentText.setText(msg.getContent());
        } else {
            showDialog("查询结果","查询失败");
        }
    }

    @Override
    public void onHttpErr(String code, String msg) {
        super.onHttpErr(code, msg);
        showDialog("查询结果","连接失败");
    }
}
