package com.example.practiceandroidapi;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.practiceandroidapi.services.SimpleFileWriter;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        renderChart();
    }

    public void reRenderChart(View view) {
        renderChart();
    }

    private void renderChart() {
        RadarChart chart = findViewById(R.id.radarChart);

        // データファイルへのパス
        String path = createDemoData();

        List<IRadarDataSet> radarData = createRadarData(path);

        RadarData data = new RadarData(radarData);
        chart.setData(data);

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
    private List<IRadarDataSet> createRadarData(String path) {
        List<IRadarDataSet> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String str;
            int i = 0;
            String[] chartLabels = {"チャートA", "チャートB", "チャートC"};
            int[] colors = {Color.BLUE, Color.RED, Color.YELLOW};
            while((str = br.readLine()) != null){

                ArrayList<RadarEntry> entry = new ArrayList<>();
                String[] strings = str.split(",");
                for (String s : strings) {
                    entry.add(new RadarEntry(Byte.valueOf(s)));
                }
                entry.add(new RadarEntry(10.125f));

                RadarDataSet chartSet = new RadarDataSet(entry, chartLabels[ i ]);
                // 色
                chartSet.setColor(colors[i]);
                // 塗りつぶし
                chartSet.setDrawFilled(true);

                // list.add(new RadarData(chartSet));
                list.add(chartSet);
                i++;
            }
            br.close();

            // return new RadarData(chartSet);
        } catch (Exception e) {
            Log.d("RadarChart", "読み込みエラー");
        }
        return list;
    }

    private String createDemoData() {
        SimpleFileWriter writer = new SimpleFileWriter();
        String path = writer.getPath(this.getFilesDir().getPath());
        writer.createDirectory(path);
        String filePath = path + "/close.txt";
        writer.write(filePath);
        return filePath;
    }
}
