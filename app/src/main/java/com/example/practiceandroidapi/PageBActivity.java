package com.example.practiceandroidapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PageBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_b);
    }

    /**
     * Go to Page C
     * Called when the user taps the Go To Page C Button.
     */
    public void goPageC(View view) {
        Intent intent = new Intent(this, PageCActivity.class);
        startActivity(intent);
    }

}
