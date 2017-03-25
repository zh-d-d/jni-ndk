package com.zdd.jnicallback;

import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

    private TextView sampleText;

    private int seconds = 0;
    private int minute = 0;
    private int hour = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sampleText = ((TextView) findViewById(R.id.sample_text));
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTicks();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTicks();
    }
    @Keep
    private void updateTimer() {

        seconds++;
        if (seconds >= 60) {
            seconds = 0;
            minute++;
            if (minute >= 60) {
                minute = 0;
                hour++;
            }
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String ticks = hour + ":" + minute + ":" + seconds;
                sampleText.setText(ticks);
            }
        });
    }


    public native void startTicks();

    public native void stopTicks();
}
