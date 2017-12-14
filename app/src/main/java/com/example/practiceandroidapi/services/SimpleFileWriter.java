package com.example.practiceandroidapi.services;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * Created by tomotaka_kato on 2017/12/14.
 */

public class SimpleFileWriter {

    private byte[] createEEGData() {
        byte[] bytes = new byte[6];
        new Random().nextBytes(bytes);
        return bytes;
    }

    public void write(String path) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for(int i=0; i < 3; i++) {
                StringBuilder sb = new StringBuilder();
                byte[] bytes = createEEGData();
                for (byte b : bytes) {
                    sb.append(b).append(",");
                }
                // 末尾のカンマ削除
                String result = sb.substring(0, sb.length() - 1);

                // ファイルに書き出し
                bw.write(result);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            Log.d("WriteTest", "書き出しエラー");
        }
        Log.d("WriteTest", "Success");

    }
}
