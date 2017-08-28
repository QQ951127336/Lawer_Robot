package com.example.a95112.lawdisplayer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by 95112 on 8/7/2017.
 */

public class FirstFragment extends Fragment {
    private PieChart pieChart;
    JsonParser jsonParser = new JsonParser();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first, container, false);
        pieChart = (PieChart) view.findViewById(R.id.PieChart);
        Intent intent = getActivity().getIntent();
        String data = intent.getStringExtra("data");
        if (data.startsWith("{")) {

            JsonObject jsonObject = (JsonObject) jsonParser.parse(data);
            float percent = jsonObject.get("percent").getAsFloat();
            PieData pieData = getPieData(percent);
            showChart(pieChart, pieData);

        }
        return  view;
    }
    private PieData getPieData(float range)
    {
        ArrayList<String> xValues = new ArrayList<String>();
        xValues.add("胜诉");
        xValues.add("败诉");
        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        float rest = 100 - range;
        yValues.add(new PieEntry(range,0));
        yValues.add(new PieEntry(rest,1));

        PieDataSet pieDataSet = new PieDataSet(yValues , "白色胜诉率");

        pieDataSet.setSliceSpace(0f);//设置个饼状图之间的距离    

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.rgb(205,205,205));
        colors.add(Color.rgb(114,188,223));

        pieDataSet.setColors(colors);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px);

        PieData pieData = new PieData(pieDataSet);
        return pieData;
    }
    private void showChart(PieChart pieChart,PieData piedata)
    {
        pieChart.setHoleRadius(60f);
        pieChart.setTransparentCircleRadius(64f);
        pieChart.setRotationAngle(90);
        pieChart.setRotationEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setData(piedata);
        Legend legend = pieChart.getLegend();
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(5f);
        pieChart.animateXY(1000,1000);
    }
}
