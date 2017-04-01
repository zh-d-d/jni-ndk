package com.zdd.ffmpegpushstream;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @CreateDate: 2017/4/1 下午5:01
 * @Author: lucky
 * @Description:
 * @Version: [v1.0]
 * <p>
 * 将视频文件以流媒体的形式推送到服务器
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
        final EditText urlEdittext_input= (EditText) this.findViewById(R.id.input_url);
        final EditText urlEdittext_output= (EditText) this.findViewById(R.id.output_url);

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0){

                String folderurl= Environment.getExternalStorageDirectory().getPath();

                String urltext_input=urlEdittext_input.getText().toString();
                String inputurl=folderurl+"/"+urltext_input;

                String outputurl=urlEdittext_output.getText().toString();

                Log.e("inputurl",inputurl);
                Log.e("outputurl",outputurl);
                String info="";

                stream(inputurl,outputurl);

                Log.e("Info",info);
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native int stream(String inputurl, String outputurl);
}
