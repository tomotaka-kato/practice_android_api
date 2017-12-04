package com.example.practiceandroidapi;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import java.util.Locale;

public class TimerActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // 読み上げ用のTTSインスタンスの作成
        tts = new TextToSpeech(this, this);
    }

    @Override
    public void onInit(int status) {
        if (TextToSpeech.SUCCESS == status) {
            //言語選択
            Locale locale = Locale.JAPAN;
            if (tts.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
                tts.setLanguage(locale);
            } else {
                Log.d("Error", "Locale");
            }
        } else {
            Log.d("Error", "Init");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(tts != null) {
            Log.d("Timer", "shut down");
            tts.shutdown();
        }
    }

    public void countTime(View view) {
        // ttsインスタンスがなければ終了
        if (tts == null) {
            Log.d("Timer", "no instance");
            return;
        }

        // カウント時間の取得
        Spinner spinner = findViewById(R.id.time_spinner);
        int second = Integer.valueOf(spinner.getSelectedItem().toString());
        tts.speak(String.valueOf(second) + "秒間カウントをします。", TextToSpeech.QUEUE_FLUSH, null);

        while (second > 0) {
            try {
                tts.speak(String.valueOf(second), TextToSpeech.QUEUE_FLUSH, null, "");
                second--;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignore
            }
        }
        Log.d("Timer", "終了");
    }
}
