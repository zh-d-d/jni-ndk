package com.zdd.ffmpegtranscoder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText cmdEdittext= (EditText) this.findViewById(R.id.editText_cmd);
        Button startButton= (Button) this.findViewById(R.id.button_start);

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0){
                String cmdline=cmdEdittext.getText().toString();
                String[] argv=cmdline.split(" ");
                Integer argc=argv.length;
                ffmpegcore(argc,argv);
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native int ffmpegcore(int argc,String[] argv);

}
