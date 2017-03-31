package com.zdd.ffmpeglib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @CreateDate: 2017/3/31 下午4:59
 * @Author: lucky
 * @Description:
 * @Version: [v1.0]
 * <p>
 * <p>
 * 本程序是移植FFmpeg到安卓平台的最简单程序。它可以打印出FFmpeg类库的下列信息：
 * Protocol:  FFmpeg类库支持的协议
 * AVFormat:  FFmpeg类库支持的封装格式
 * AVCodec:   FFmpeg类库支持的编解码器
 * AVFilter:  FFmpeg类库支持的滤镜
 * Configure: FFmpeg类库的配置信息
 */

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @BindView(R.id.btn_configuration)
    Button mBtnConfiguration;
    @BindView(R.id.btn_protocol)
    Button mBtnProtocol;
    @BindView(R.id.btn_format)
    Button mBtnFormat;
    @BindView(R.id.btn_codec)
    Button mBtnCodec;
    @BindView(R.id.btn_filter)
    Button mBtnFilter;
    @BindView(R.id.tv_info)
    TextView mTvInfo;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String urlprotocolinfo();

    public native String avformatinfo();

    public native String avcodecinfo();

    public native String avfilterinfo();

    public native String configurationinfo();


    @OnClick({R.id.btn_configuration, R.id.btn_protocol, R.id.btn_format, R.id.btn_codec, R.id.btn_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_configuration:
                mTvInfo.setText(configurationinfo());
                break;
            case R.id.btn_protocol:
                mTvInfo.setText(urlprotocolinfo());
                break;
            case R.id.btn_format:
                mTvInfo.setText(avformatinfo());
                break;
            case R.id.btn_codec:
                mTvInfo.setText(avcodecinfo());
                break;
            case R.id.btn_filter:
                mTvInfo.setText(avfilterinfo());
                break;
        }
    }
}
