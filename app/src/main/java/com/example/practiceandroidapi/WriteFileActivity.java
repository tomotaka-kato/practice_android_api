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

    private FileOutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_file);
    }

    /**
     * 現在時刻のタイムスタンプでディレクトリ名を作成する
     * @return
     */
    private String getPath() {
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        // /data/data/パッケージ名/files 以下をパスにする。
        // return "/data/data/" + this.getPackageName() + "/files/" + f.format(d);
        return this.getFilesDir() + "/" + f.format(d);
    }

    private void createDirectory(String path) {
        File file = new File(path);
        if(file.exists()) {
            Log.d("WriteTest", "既にファイルが存在します。");
        } else {
            file.mkdirs();
        }
    }

    public void writeFile(View view) {
        Log.d("WriteTest", "start");

        // 書き出し先ディレクトリのパス取得
        String dirPath = getPath();
        // ディレクトリの作成
        createDirectory(dirPath);
        new SimpleFileWriter().write(dirPath + "/close.txt");
    }
}
