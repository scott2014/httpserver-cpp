package com.yhj.httpclient;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yhj.httpclient.model.BaseResponse;
import com.yhj.httpclient.model.HttpListener;
import com.yhj.httpclient.model.HttpTask;
import com.yhj.httpclient.model.Msg;
import com.yhj.httpclient.model.UrlConst;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MsgActivity extends BaseActivity {
    private Button mAddBtn;
    private ListView mMsgList;

    private HttpTask task = new HttpTask(this);

    private List<Msg> msgs = new ArrayList<Msg>();

    private MsgAdapter adapter;

    private Dialog dialog;

    private HttpTask delTask = new HttpTask(new HttpListener() {
        @Override
        public void onHttpOk(BaseResponse response) {
            dialog.dismiss();
            if ("0".equals(response.getCode())) {
                showDialog("删除结果","删除成功!");
            } else {
                showDialog("删除结果","删除失败!");
            }
            HttpPost post = new HttpPost(UrlConst.URL_MSG_LIST);
            JSONObject json = new JSONObject();
            try {
                json.put("userid",getSharePref("userid"));
                post.setEntity(new StringEntity(json.toString(),"UTF-8"));
                task.execute(post);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onHttpErr(String code, String msg) {
            dialog.dismiss();
            showDialog("删除结果","连接异常");
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        this.mAddBtn = (Button) this.findViewById(R.id.btn_add);
        this.mMsgList = (ListView) this.findViewById(R.id.listview_msg);

        this.mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go(AddMsgActivity.class);
            }
        });

        this.mMsgList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Msg msg = msgs.get(i);
                Intent intent = new Intent(MsgActivity.this,MsgDetailActivity.class);
                intent.putExtra("msgid",msg.getId());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_msg, menu);
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

        Log.e("data", String.valueOf(response.getData()));

      /*  try {
            JSONObject json = new JSONObject(String.valueOf(response.getData()));
            JSONArray arr = json.getJSONArray("data");
            for (int i=0;i<arr.length();i++) {
                Msg msg = new Msg();
                msg.setId(arr.getString());
                msg.setTitle(arr.getString(0));
                msg.setContent(arr.getString(2));

                msgs.add(msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        msgs = new Gson().fromJson(String.valueOf(response.getData()), new TypeToken<List<Msg>>() {
        }.getType());

        if (msgs != null && msgs.size() > 0) {
            if (adapter == null) {
                adapter = new MsgAdapter(msgs);
                this.mMsgList.setAdapter(adapter);
            } else {
                adapter.setMsgs(msgs);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onHttpErr(String code, String msg) {
        super.onHttpErr(code, msg);
    }

    @Override
    protected void onResume() {
        super.onResume();

        HttpPost post = new HttpPost(UrlConst.URL_MSG_LIST);
        JSONObject json = new JSONObject();
        try {
            json.put("userid",getSharePref("userid"));
            post.setEntity(new StringEntity(json.toString(),"UTF-8"));
            task.execute(post);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    class MsgAdapter extends BaseAdapter {

        private List<Msg> msgs;

        MsgAdapter(List<Msg> msgs) {
            this.msgs = msgs;
        }

        @Override
        public int getCount() {
            return msgs.size();
        }

        @Override
        public Object getItem(int i) {
            return msgs.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int pos, View view, ViewGroup viewGroup) {

            ViewHolder viewHolder;
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.item_msg,null);

                viewHolder = new ViewHolder();
                viewHolder.title = (TextView) view.findViewById(R.id.tv_title);
                viewHolder.close = (TextView) view.findViewById(R.id.tv_close);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.title.setTag(msgs.get(pos).getId());

            viewHolder.title.setText(msgs.get(pos).getTitle());

            viewHolder.close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showYesNoDialog("删除记录","确定删除吗?",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            switch (i) {
                                case DialogInterface.BUTTON_NEGATIVE:
                                    dialog = showProgressDialog();
                                    HttpPost post = new HttpPost(UrlConst.URL_MSG_DELETE);
                                    String msgid = msgs.get(pos).getId();
                                    try {
                                        JSONObject json = new JSONObject();
                                        json.put("msgid",msgid);
                                        post.setEntity(new StringEntity(json.toString(),"UTF-8"));
                                        delTask.execute(post);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case DialogInterface.BUTTON_POSITIVE:
                                    dialogInterface.dismiss();
                                    break;
                            }
                        }
                    });
                }
            });

            return view;
        }

        public List<Msg> getMsgs() {
            return msgs;
        }

        public void setMsgs(List<Msg> msgs) {
            this.msgs = msgs;
        }
    }

    class ViewHolder {
        TextView title;
        TextView close;
    }
}
