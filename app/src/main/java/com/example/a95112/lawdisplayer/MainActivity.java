package com.example.a95112.lawdisplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetWorkChangeBroadcast netWorkChangeBroadcast;
    RadioButton q1_0,q1_1,q2_0,q2_1,q3_1,q3_0,q4_1,q4_0,q5_1,q5_0,q6_1,q6_0,q7_0,q7_1,q8_1,q8_0,q9_1,q9_0,q10_1,q10_0,q11_1,q11_0,q12_1,q12_0;
    String last_meg = "";
    Button submit ;
    JSONObject json = new JSONObject();
    String get_resp = "";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("data",last_meg);
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    byte[] ans = new byte[12];
    Runnable network = new Runnable() {
        @Override
        public void run() {
            try {
                get_resp =  post(" http://0c1d09f0.ngrok.io/myapp/answer/",last_meg);
                Log.d("post",get_resp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("test",get_resp);

            Intent intent = new Intent(MainActivity.this, ContentActivity.class);
            intent.putExtra("data",get_resp);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECT");
        netWorkChangeBroadcast = new NetWorkChangeBroadcast();
        registerReceiver(netWorkChangeBroadcast,intentFilter);


        init();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                last_meg = "";
                if (q1_1.isChecked())
                    ans[0] = 1;
                else
                    ans[0] = 0;
                if (q2_1.isChecked())
                    ans[1] = 1;
                else
                    ans[1] = 0;
                if (q3_1.isChecked())
                    ans[2] = 1;
                else
                    ans[2] = 0;
                if (q4_1.isChecked())
                    ans[3] = 1;
                else
                    ans[3] = 0;
                if (q5_1.isChecked())
                    ans[4] = 1;
                else
                    ans[4] = 0;
                if (q6_1.isChecked())
                    ans[5] = 1;
                else
                    ans[5] = 0;
                if (q7_1.isChecked())
                    ans[6] = 1;
                else
                    ans[6] = 0;
                if (q8_1.isChecked())
                    ans[7] = 1;
                else
                    ans[7] = 0;
                if (q9_1.isChecked())
                    ans[8] = 1;
                else
                    ans[8] = 0;
                if (q10_1.isChecked())
                    ans[9] = 1;
                else
                    ans[9] = 0;
                if (q11_1.isChecked())
                    ans[10] = 1;
                else
                    ans[10] = 0;
                if (q12_1.isChecked())
                    ans[11] = 1;
                else
                    ans[11] = 0;
                for (int i = 0 ;i<12;i++)
                {
                    last_meg = last_meg+ans[i];
                }


                new Thread(network).start();
            }
        });

    }
    @Override
    protected void onDestroy()
    {

        super.onDestroy();
        unregisterReceiver(netWorkChangeBroadcast);
    }
    private void init()
    {
        submit = (Button)findViewById(R.id.submit);
        q1_0=(RadioButton)findViewById(R.id.q1_0);
        q1_1=(RadioButton)findViewById(R.id.q1_1);
        q2_0=(RadioButton)findViewById(R.id.q2_0);
        q2_1=(RadioButton)findViewById(R.id.q2_1);
        q3_0=(RadioButton)findViewById(R.id.q3_0);
        q3_1=(RadioButton)findViewById(R.id.q3_1);
        q4_0=(RadioButton)findViewById(R.id.q4_0);
        q4_1=(RadioButton)findViewById(R.id.q4_1);
        q5_0=(RadioButton)findViewById(R.id.q5_0);
        q5_1=(RadioButton)findViewById(R.id.q5_1);
        q6_0=(RadioButton)findViewById(R.id.q6_0);
        q6_1=(RadioButton)findViewById(R.id.q6_1);
        q7_0=(RadioButton)findViewById(R.id.q7_0);
        q7_1=(RadioButton)findViewById(R.id.q7_1);
        q8_0=(RadioButton)findViewById(R.id.q8_0);
        q8_1=(RadioButton)findViewById(R.id.q8_1);
        q9_0=(RadioButton)findViewById(R.id.q9_0);
        q9_1=(RadioButton)findViewById(R.id.q9_1);
        q10_0=(RadioButton)findViewById(R.id.q10_0);
        q10_1=(RadioButton)findViewById(R.id.q10_1);
        q11_0=(RadioButton)findViewById(R.id.q11_0);
        q11_1=(RadioButton)findViewById(R.id.q11_1);
        q12_0=(RadioButton)findViewById(R.id.q12_0);
        q12_1=(RadioButton)findViewById(R.id.q12_1);
    }
    class NetWorkChangeBroadcast extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}
