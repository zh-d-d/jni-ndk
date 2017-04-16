package com.zdd.ffmpgelive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_bt_live).setOnClickListener(this);
        findViewById(R.id.main_bt_watch).setOnClickListener(this);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String liveFromFFmpeg();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_bt_live:
                Intent liveIntent = new Intent(this, LiveActivity.class);
                startActivity(liveIntent);
                break;

            case R.id.main_bt_watch:
                break;
        }
    }
}
