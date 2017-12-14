package com.example.practiceandroidapi;

import android.content.Context;
import android.icu.util.Output;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.practiceandroidapi.services.SimpleFileWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteFileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_file);
    }


    public void writeFile(View view) {
        Log.d("WriteTest", "start");

        // 書き出し先ディレクトリのパス取得
        SimpleFileWriter writer = new SimpleFileWriter();
        String path = writer.getPath(this.getFilesDir().getPath());
        // ディレクトリの作成
        writer.createDirectory(path);
        // ファイルの書き出し
        writer.write(path + "/close.txt");
    }
}
