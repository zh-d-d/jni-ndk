package com.zdd.cameratoyuv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zdd.cameratoyuv.widget.CameraSurfaceView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button startRecord;
    private Button switchOrientation;
    private CameraSurfaceView cameraContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void init() {
        startRecord = ((Button) findViewById(R.id.startRecord));
        startRecord.setOnClickListener(this);

        switchOrientation = ((Button) findViewById(R.id.switchOrientation));
        switchOrientation.setOnClickListener(this);

        cameraContent = ((CameraSurfaceView) findViewById(R.id.cameraSurface));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startRecord:
                cameraContent.statrRecored();
                break;
            case R.id.switchOrientation:
                cameraContent.stopRecored();
                break;
        }
    }

}
