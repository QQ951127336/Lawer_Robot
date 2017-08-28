package com.example.a95112.lawdisplayer;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by 95112 on 8/7/2017.
 */

public class ThirdFragment extends Fragment {
    ListView listView;
    JsonParser parser = new JsonParser();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.third,container,false);
        listView = (ListView)view.findViewById(R.id.listView);
        Intent intent = getActivity().getIntent();
        String data = intent.getStringExtra("data");
        if (data.startsWith("{"))
        {
            JsonObject jsonObject = (JsonObject)parser.parse(data);
            JsonArray jsonArray = jsonObject.get("rule").getAsJsonArray();
            String[] rules = new String [jsonArray.size()];
            for (int i = 0;i<jsonArray.size();i++)
            {
                rules[i] = jsonArray.get(i).getAsString();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,rules);
            listView.setAdapter(adapter);
        }
        return view;
    }
}
