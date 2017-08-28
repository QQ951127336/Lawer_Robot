package com.example.a95112.lawdisplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by 95112 on 8/4/2017.
 */

public class InformationActivity extends Activity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inforpage);
        Intent intent = getIntent();
        listView = (ListView)findViewById(R.id.aboutissue);
        String data = intent.getStringExtra("page");
        if (data.startsWith("{"))
        {
            JsonParser parser = new JsonParser();

            JsonObject jsonObject =(JsonObject)parser.parse(data);
            JsonArray jsonArray = jsonObject.get("article").getAsJsonArray();
            String page = "";
            String[] pages = new String[jsonArray.size()];
            for (int i =0 ;i<jsonArray.size();i++)
            {
                pages[i] =  jsonArray.get(i).getAsString();
                Log.e("e",pages[i]);
                page = page + jsonArray.get(i).getAsString();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(InformationActivity.this,android.R.layout.simple_list_item_1,pages);
            listView.setAdapter(adapter);

        }



    }
}
