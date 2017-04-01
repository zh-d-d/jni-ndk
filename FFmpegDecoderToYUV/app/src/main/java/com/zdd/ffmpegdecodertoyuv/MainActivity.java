package com.zdd.ffmpegdecodertoyuv;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @CreateDate: 2017/4/1 上午11:16
 * @Author: lucky
 * @Description:
 * @Version: [v1.0]
 * <p>
 * 最简单的基于FFmpeg的视频解码器。它可以将输入的视频数据解码成YUV像素数据。
 */

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = (Button) this.findViewById(R.id.button_start);
        final EditText urlEdittext_input = (EditText) this.findViewById(R.id.input_url);
        final EditText urlEdittext_output = (EditText) this.findViewById(R.id.output_url);

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                String folderurl = Environment.getExternalStorageDirectory().getPath();

                String urltext_input = urlEdittext_input.getText().toString();
                String inputurl = folderurl + "/" + urltext_input;

                String urltext_output = urlEdittext_output.getText().toString();
                String outputurl = folderurl + "/" + urltext_output;

                Log.i("inputurl", inputurl);
                Log.i("outputurl", outputurl);

                decode(inputurl, outputurl);

            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    //JNI
    public native int decode(String inputurl, String outputurl);
}
