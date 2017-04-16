package com.zdd.cameratoyuv.widget;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.zdd.cameratoyuv.util.CameraUtil;
import com.zdd.cameratoyuv.util.LogUtils;

import java.io.IOException;

/**
 * @CreateDate: 2017/4/16 下午5:36
 * @Author: lucky
 * @Description:
 * @Version: [v1.0]
 */

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Camera.PreviewCallback {

    private static final String TAG = "CameraSurfaceView";

    private Camera mCamera;
    private SurfaceHolder mHolder;
    //    摄像头数量
    private int mCamerasCount;
    //    摄像头当前方向--默认为打开后置摄像头
    private int cameraFacing = Camera.CameraInfo.CAMERA_FACING_BACK;

    private Context mContext;

    public CameraSurfaceView(Context context) {
        super(context);
        init(context);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mHolder = getHolder();
        mHolder.addCallback(this);

        mCamerasCount = Camera.getNumberOfCameras();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!(mCamerasCount > 0)) {
            LogUtils.e(TAG, "当前设备没有摄像头");
            return;
        }
        initCamera();
    }

    private void initCamera() {
//
        if (CameraUtil.checkCameraFacing(cameraFacing)) {
            try {
                mCamera = Camera.open(cameraFacing);
                mCamera.setPreviewDisplay(mHolder);

                mCamera.setDisplayOrientation(CameraUtil.getPreviewDegree(mContext));
                mCamera.setPreviewCallback(this);
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCamera.setPreviewCallback(null);
    }


    public int getCameraFacing() {
        return cameraFacing;
    }

    public void setCameraFacing(int cameraFacing) {
        this.cameraFacing = cameraFacing;
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        LogUtils.i(TAG,data);

    }

    public void statrRecored(){
        LogUtils.i(TAG,"start recored");
        
    }

    public void stopRecored(){
        LogUtils.i(TAG,"stop recored");
    }

}
