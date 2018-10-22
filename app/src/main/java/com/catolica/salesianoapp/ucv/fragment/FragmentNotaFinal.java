package com.catolica.salesianoapp.ucv.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catolica.salesianoapp.ucv.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class FragmentNotaFinal extends Fragment {
    private BarChart mChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notasfinal, container, false);

        mChart = (BarChart) v.findViewById(R.id.chart1);

        mChart.getDescription().setEnabled(false);

        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        mChart.getAxisLeft().setDrawGridLines(false);

        mChart.getLegend().setEnabled(false);

        setData(7);
        mChart.setFitBars(true);

        return v;
    }

    private void setData(int count) {

        final int redColor = Color.parseColor("#EF5350");
        final int greenColor = Color.parseColor("#66BB6A");
        final int orangeColor = Color.parseColor("#FFA726");

        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        List<Integer> cores = new ArrayList<>();

        List<Float> valores = new ArrayList<>();
        valores.add((float) -1);
        valores.add(Float.valueOf(77));
        valores.add((float) 40);
        valores.add((float) 60);
        valores.add((float) 82.5);
        valores.add((float) 78.6);
        valores.add((float) 77);

        for (int pos = 1; pos < valores.size(); pos++){
            float nota = valores.get(pos);
            yVals.add(new BarEntry(pos,valores.get(pos)));
            if(nota>70){
                cores.add(greenColor);
            }
            if((nota>=60) && (nota<=70)){
                cores.add(orangeColor);
            }
            if(nota<60){
                cores.add(redColor);
            }
        }
        BarDataSet set = new BarDataSet(yVals,"Notas");
        set.setColors(cores);

        set.setDrawValues(true);

        BarData data = new BarData(set);

        mChart.setData(data);
        mChart.invalidate();
        mChart.animateY(800);
    }

}
