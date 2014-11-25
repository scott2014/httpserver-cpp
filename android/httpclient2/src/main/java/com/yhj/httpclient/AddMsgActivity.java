package com.yhj.httpclient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yhj.httpclient.model.BaseResponse;
import com.yhj.httpclient.model.HttpTask;
import com.yhj.httpclient.model.UrlConst;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class AddMsgActivity extends BaseActivity {
    private EditText mTitleEdit;
    private EditText mContentEdit;

    private Button mSubBtn;
    private ProgressDialog dialog;

    private HttpTask task = new HttpTask(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_msg);

        this.mTitleEdit = (EditText) this.findViewById(R.id.et_msg_title);
        this.mContentEdit = (EditText) this.findViewById(R.id.et_msg_content);
        this.mSubBtn = (Button) this.findViewById(R.id.btn_sub);

        this.mSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = showProgressDialog();
                String title = mTitleEdit.getText().toString();
                String content = mContentEdit.getText().toString();
                String userid = getSharePref("userid");

                JSONObject json = new JSONObject();
                try {
                    json.put("title",title);
                    json.put("content",content);
                    json.put("userid",userid);
                    HttpPost post = new HttpPost(UrlConst.URL_MSG_ADD);
                    post.setEntity(new StringEntity(json.toString(),"UTF-8"));
                    Log.i("msg", json.toString());
                    task.execute(post);
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
        getMenuInflater().inflate(R.menu.menu_add_msg, menu);
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
            finish();
        } else {
            showDialog("添加结果","添加失败");
        }
    }

    @Override
    public void onHttpErr(String code, String msg) {
        super.onHttpErr(code, msg);
    }
}
