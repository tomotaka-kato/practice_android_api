package com.example.practiceandroidapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PageCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_c);
    }

    /**
     * Go to Page A
     * Called when the user taps the Go To Page A Button.
     */
    public void goPageANormal(View view) {
        Intent intent = new Intent(this, PageAActivity.class);
        startActivity(intent);
    }

    /**
     * Go to Page A
     * Called when the user taps the Go To Page A Button.
     */
    public void goPageADeleteStacks(View view) {
        Intent intent = new Intent(this, PageAActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
