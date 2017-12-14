package com.example.practiceandroidapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Go to Timer page.
     * Called when the user taps the Go To Timer Button.
     */
    public void goToTimer(View view) {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }

    /**
     * Go to BLE page.
     * Called when the user taps the Go To BLE Button.
     */
    public void goToBLE(View view) {
        Intent intent = new Intent(this, BLEActivity.class);
        startActivity(intent);
    }

    /**
     * Go to Page A
     * Called when the user taps the Go To Page Transition Button.
     */
    public void goPageTransition(View view) {
        Intent intent = new Intent(this, PageAActivity.class);
        startActivity(intent);
    }

    /**
     * チャート画面への遷移
     * @param view
     */
    public void goChart(View view) {
        Intent intent = new Intent(this, ChartActivity.class);
        startActivity(intent);
    }

}
