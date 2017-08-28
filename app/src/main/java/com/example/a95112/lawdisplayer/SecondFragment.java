package com.example.a95112.lawdisplayer;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by 95112 on 8/7/2017.
 */

public class SecondFragment extends Fragment {
    private TextView t1,t2,t3;
    JsonParser jsonParser = new JsonParser();
    String name1,name2,name3,name;
    String baseUrl = "http://0c1d09f0.ngrok.io/myapp/article/";
    OkHttpClient client = new OkHttpClient();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.second,container,false);
        t1 = (TextView)view.findViewById(R.id.t1);
        t2 = (TextView)view.findViewById(R.id.t2);
        t3 = (TextView)view.findViewById(R.id.t3);
        Intent intent = getActivity().getIntent();
        String data =intent.getStringExtra("data");
        if (data.startsWith("{"))
        {
            JsonObject jsonObject = (JsonObject) jsonParser.parse(data);
            JsonArray articleArray = jsonObject.get("article").getAsJsonArray();
            name1 = articleArray.get(0).getAsString();
            name2 = articleArray.get(1).getAsString();
            name3 = articleArray.get(2).getAsString();


            t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = name1;
                    if (name1.length()>2)
                    {

                        new Thread(moreInformation).start();


                    }
                }
            });
            t2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = name2;
                    if (name1.length()>2)
                    {

                        new Thread(moreInformation).start();


                    }
                }
            });
            t3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = name3;
                    if (name1.length()>2)
                    {

                        new Thread(moreInformation).start();


                    }
                }
            });

        }

        return view;

    }
    String post(String url) throws IOException {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("data",name);
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    Runnable moreInformation = new Runnable() {
        @Override
        public void run() {
            try{
                String response = post(baseUrl);
                Intent intent = new Intent(getActivity(),InformationActivity.class);
                intent.putExtra("page",response);
                startActivity(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
