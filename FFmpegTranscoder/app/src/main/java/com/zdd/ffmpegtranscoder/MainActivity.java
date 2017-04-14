package com.zdd.ffmpegtranscoder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @CreateDate: 2017/4/14 下午3:18
 * @Author: lucky
 * @Description:
 * @Version: [v1.0]
 * <p>
 * 这里需要注意 视频的路径  视频的路径  视频的路径 坑死了我两天
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

        // Example of a call to a native method
        final EditText cmdEdittext = (EditText) this.findViewById(R.id.editText_cmd);

        Button transcoder = (Button) findViewById(R.id.button_start);
        transcoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cmdline = cmdEdittext.getText().toString();
                String[] argv = cmdline.split(" ");
                Integer argc = argv.length;
                ffmpegcore(argc, argv);
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native int ffmpegcore(int argc, String[] argv);
}
