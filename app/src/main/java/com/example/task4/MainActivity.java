package com.example.task4;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity implements View.OnClickListener {
    final String LOG_TAG = "myLogs";
    TextView textView;
    Button button;
    Handler handler;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                textView.setText("Закачано файлов: " + msg.what);
                if (msg.what == 10) button.setEnabled(true);
            }
        };
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                button.setEnabled(false);
                Thread th1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 1; i <= 10; i++) {
                            downloadFile();
                            textView.setText("Закачано файлов: " + i);
                            Log.d(LOG_TAG, "Закачано файлов: " + i);
                        }
                    }
                });
                th1.start();
                break;
            default:
                break;
        }
    }

    void downloadFile() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {}
    }
}
