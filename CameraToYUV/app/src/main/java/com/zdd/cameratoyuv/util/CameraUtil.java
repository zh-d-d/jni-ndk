package com.zdd.cameratoyuv.util;

import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.view.Surface;
import android.view.WindowManager;

/**
 * @CreateDate: 2017/4/16 下午6:01
 * @Author: lucky
 * @Description:
 * @Version: [v1.0]
 * <p>
 * 由于getNumberOfCameras以及getCameraInfo均为API 9 引入，所以方法只适用于2.3及其以上
 */

public class CameraUtil {
    /**
     * 是否支持后置摄像头
     *
     * @return
     */
    public static boolean hasBackFacingCamera() {
        final int CAMERA_FACING_BACK = 0;
        return checkCameraFacing(CAMERA_FACING_BACK);
    }

    /**
     * 是否支持前置摄像头
     *
     * @return
     */
    public static boolean hasFrontFacingCamera() {
        final int CAMERA_FACING_BACK = 1;
        return checkCameraFacing(CAMERA_FACING_BACK);
    }

    public static boolean checkCameraFacing(final int facing) {
        if (getSdkVersion() < Build.VERSION_CODES.GINGERBREAD) {
            return false;
        }
        final int cameraCount = Camera.getNumberOfCameras();
        Camera.CameraInfo info = new Camera.CameraInfo();
        for (int i = 0; i < cameraCount; i++) {
            Camera.getCameraInfo(i, info);
            if (facing == info.facing) {
                return true;
            }
        }
        return false;
    }

    public static int getSdkVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    //=============================================================

    /**
     * 用于根据手机方向获得相机预览画面旋转的角度
     *
     * @param mContext
     * @return
     */
    public static int getPreviewDegree(Context mContext) {
        // 获得手机的方向
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int rotation = windowManager.getDefaultDisplay()
                .getRotation();

        int degree = 0;
        // 根据手机的方向计算相机预览画面应该选择的角度
        switch (rotation) {
            case Surface.ROTATION_0:
                degree = 90;
                break;
            case Surface.ROTATION_90:
                degree = 0;
                break;
            case Surface.ROTATION_180:
                degree = 270;
                break;
            case Surface.ROTATION_270:
                degree = 180;
                break;
        }
        return degree;
    }
}
