package com.example.a95112.lawdisplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by 95112 on 8/7/2017.
 */

public class ContentActivity extends FragmentActivity implements View.OnClickListener {

    private FirstFragment fg1;
    private SecondFragment fg2;
    private ThirdFragment fg3;
    ListView listView;

    Button b1,b2,b3;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoumessage1);
        fragmentManager = getSupportFragmentManager();
        init();
        setChoice(0);

    }
    @Override
    public void onClick(View v) {

    }
    private void init()
    {
        listView = (ListView)findViewById(R.id.listView);
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoice(0);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoice(1);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoice(2);
            }
        });

    }
    private void setChoice(int index)
    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        clearChoice();
        hiddFragment(fragmentTransaction);
        switch (index)
        {
            case 0:
                b1.setBackgroundColor((0xFFa5e2df));
                b2.setBackgroundColor((0xFFd5d6d6));
                b3.setBackgroundColor((0xFFd5d6d6));
                if(fg1==null)
                {
                    fg1 = new FirstFragment();
                    fragmentTransaction.add(R.id.content,fg1);
                }
                else
                {
                    fragmentTransaction.show(fg1);
                }
                break;
            case 1:
                b2.setBackgroundColor((0xFFa5e2df));
                b1.setBackgroundColor((0xFFd5d6d6));
                b3.setBackgroundColor((0xFFd5d6d6));
                if (fg2 == null)
                {
                    fg2 = new SecondFragment();
                    fragmentTransaction.add(R.id.content,fg2);
                }
                else
                {
                    fragmentTransaction.show(fg2);
                }
                break;
            case 2:
                b3.setBackgroundColor((0xFFa5e2df));
                b2.setBackgroundColor((0xFFd5d6d6));
                b1.setBackgroundColor((0xFFd5d6d6));
                if (fg3 == null)
                {
                    fg3 = new ThirdFragment();
                    fragmentTransaction.add(R.id.content,fg3);
                }
                else
                {
                    fragmentTransaction.show(fg3);
                }
                break;

        }
        fragmentTransaction.commit();

    }

    private void hiddFragment(FragmentTransaction fragmentTransaction) {
        if (fg1!=null)
        {
            fragmentTransaction.hide(fg1);
        }
        if (fg2!=null)
        {
            fragmentTransaction.hide(fg2);
        }
        if (fg3!=null)
        {
            fragmentTransaction.hide(fg3);
        }


    }

    private void clearChoice() {

    }
}
