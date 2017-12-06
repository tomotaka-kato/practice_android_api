package com.example.practiceandroidapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PageAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_a);
    }


    /**
     * Go to Page B
     * Called when the user taps the Go To Page B Button.
     */
    public void goPageB(View view) {
        Intent intent = new Intent(this, PageBActivity.class);
        startActivity(intent);
    }
}
