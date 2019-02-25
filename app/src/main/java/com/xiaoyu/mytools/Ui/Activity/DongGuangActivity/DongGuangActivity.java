package com.xiaoyu.mytools.Ui.Activity.DongGuangActivity;


import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.zxing.Result;
import com.xiaoyu.mytools.Base.MyActivity;
import com.xiaoyu.mytools.Interface.OnRxScanerListener;
import com.xiaoyu.mytools.R;
import com.xiaoyu.mytools.Tools.MyBeepTool;
import com.xiaoyu.mytools.Tools.MyPhotoTool;
import com.xiaoyu.mytools.Tools.MyVibrateTool;
import com.xiaoyu.mytools.Utils.T;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yfb on 2019/2/21.
 */

public class DongGuangActivity extends MyActivity {
    private static OnRxScanerListener mScanerListener;//扫描结果监听
    private InactivityTimer inactivityTimer;
    private CaptureActivityHandler handler;//扫描处理
    private RelativeLayout mContainer = null;//整体根布局
    private RelativeLayout mCropLayout = null;//扫描框根布局
    private int mCropWidth = 0;//扫描边界的宽度
    private int mCropHeight = 0;//扫描边界的高度
    private boolean hasSurface;//是否有预览
    private boolean vibrate = true;//扫描成功后是否震动
    private boolean mFlashing = true;//闪光灯开启状态
    @BindView(R.id.light)
    Button mLight;
    @BindView(R.id.btn_vibrate_once)
    Button mBtnVibrateOnce;
    @BindView(R.id.btn_vibrate_Complicated)
    Button mBtnVibrateComplicated;
    @BindView(R.id.btn_vibrate_stop)
    Button mBtnVibrateStop;
    private long[] temp = {100, 10, 100, 1000};

    @Override
    public int setLayout() {
        return R.layout.activity_dongguang;
    }

    @Override
    protected void initView() {

        ButterKnife.bind(this);

        CameraManager.init(DongGuangActivity.this);//初始化 CameraManager
    }

    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.capture_preview);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);//Camera初始化
        } else {
            surfaceHolder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    if (!hasSurface) {
                        hasSurface = true;
                        initCamera(holder);
                    }
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    hasSurface = false;

                }
            });
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        mScanerListener = null;
        super.onDestroy();
    }

    private void light() {
        if (mFlashing) {
            mFlashing = false;
            // 开闪光灯
            CameraManager.get().openLight();
        } else {
            mFlashing = true;
            // 关闪光灯
            CameraManager.get().offLight();
        }

    }

    @OnClick({R.id.light,R.id.top_openpicture, R.id.btn_vibrate_once, R.id.btn_vibrate_Complicated, R.id.btn_vibrate_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_openpicture:
                MyPhotoTool.openLocalImage(DongGuangActivity.this);
                break;
            case R.id.light:
                light();
                break;
            case R.id.btn_vibrate_once:
                MyVibrateTool.vibrateOnce(this, 2000);
                break;
            case R.id.btn_vibrate_Complicated:
                MyVibrateTool.vibrateComplicated(this, temp, 0);
                break;
            case R.id.btn_vibrate_stop:
                MyVibrateTool.vibrateStop();
                break;
        }
    }


    //初始化CameraManager
    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
            Point point = CameraManager.get().getCameraResolution();
            AtomicInteger width = new AtomicInteger(point.y);
            AtomicInteger height = new AtomicInteger(point.x);
            int cropWidth = mCropLayout.getWidth() * width.get() / mContainer.getWidth();
            int cropHeight = mCropLayout.getHeight() * height.get() / mContainer.getHeight();
            setCropWidth(cropWidth);
            setCropHeight(cropHeight);
        } catch (IOException | RuntimeException ioe) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(DongGuangActivity.this);
        }
    }

    public void setCropWidth(int cropWidth) {
        mCropWidth = cropWidth;
        CameraManager.FRAME_WIDTH = mCropWidth;

    }

    public int getCropHeight() {
        return mCropHeight;
    }

    public void setCropHeight(int cropHeight) {
        this.mCropHeight = cropHeight;
        CameraManager.FRAME_HEIGHT = mCropHeight;
    }

    public Handler getHandler() {
        return handler;
    }

    public void handleDecode(Result result) {
        inactivityTimer.onActivity();
        MyBeepTool.playBeep(DongGuangActivity.this, vibrate);//扫描成功之后的振动与声音提示

        String result1 = result.getText();
        Log.v("二维码/条形码 扫描结果", result1);
        if (mScanerListener == null) {

            //initDialogResult(result);
        } else {
            mScanerListener.onSuccess("From to Camera", result);
        }
    }

    //--------------------------------------打开本地图片识别二维码 start---------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (resultCode == Activity.RESULT_OK) {
            ContentResolver resolver = getContentResolver();
            // 照片的原始资源地址
            Uri originalUri = data.getData();
            try {
                // 使用ContentProvider通过URI获取原始图片
                Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);

                // 开始对图像资源解码
                Result rawResult = RxQrBarTool.decodeFromPhoto(photo);
                if (rawResult != null) {
                    if (mScanerListener == null) {
                        initDialogResult(rawResult);
                    } else {
                        mScanerListener.onSuccess("From to Picture", rawResult);
                    }
                } else {
                    if (mScanerListener == null) {
                        RxToast.error("图片识别失败.");
                    } else {
                        mScanerListener.onFail("From to Picture", "图片识别失败");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        T.showShort("相册关闭");
    }
    //==============================================================================================解析结果 及 后续处理 end

}
