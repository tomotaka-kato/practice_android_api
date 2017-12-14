package com.example.practiceandroidapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;


public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        renderChart();
    }

    private void renderChart() {
        RadarChart chart = findViewById(R.id.radarChart);

        RadarData radarData = createRadarData();

        chart.setData(radarData);
        chart.animateXY(1000, 1000);

        // ラベルの表示
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"テスト1", "テスト2", "テスト3", "テスト4", "テスト5"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });


        // チャートの表示実行
        chart.invalidate();
    }

    /** レーダーチャートのデータを作成し、返却する */
    private RadarData createRadarData() {
        ArrayList<RadarEntry> entry = new ArrayList<>();

        entry.add(new RadarEntry(10.125f, 0));
        entry.add(new RadarEntry( 20.5f, 2));
        entry.add(new RadarEntry(32.32f, 1));
        entry.add(new RadarEntry(16.0f, 3));
        entry.add(new RadarEntry(28.893f, 4));

        RadarDataSet chartSet = new RadarDataSet(entry, "脳波測定結果");
        // 塗りつぶし
        chartSet.setDrawFilled(true);

        return new RadarData(chartSet);
    }
}
