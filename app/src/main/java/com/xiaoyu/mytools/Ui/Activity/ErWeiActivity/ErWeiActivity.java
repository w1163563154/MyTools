package com.xiaoyu.mytools.Ui.Activity.ErWeiActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.xiaoyu.mytools.Base.MyActivity;
import com.xiaoyu.mytools.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yfb on 2019/2/22.
 */

public class ErWeiActivity extends MyActivity {
    @BindView(R.id.Button1)
    Button mButton1;
    @BindView(R.id.Button2)
    Button mButton2;
    @BindView(R.id.Button3)
    Button mButton3;
    @BindView(R.id.image1)
    ImageView mImage1;

    @Override
    public int setLayout() {
        return R.layout.activity_erwei;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }



    @OnClick({R.id.Button1, R.id.Button2, R.id.Button3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Button1: //无图片二维码
                //二维码生成方式一  推荐此方法
                MyQRCode.builder("https://www.baidu.com/").
                        backColor(getResources().getColor(R.color.white)).
                        codeColor(getResources().getColor(R.color.black)).
                        codeSide(600).
                        into(mImage1);

                //二维码生成方式二 默认宽和高都为800 背景为白色 二维码为黑色
                // RxQRCode.createQRCode(str,mIvQrCode);
                break;
            case R.id.Button2: //带图片二维码

                Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
                Bitmap  bitmap = null;
                try {
                    bitmap =  MyQRCode.createQRImage("https://www.baidu.com/",400,400,logo);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                mImage1.setImageBitmap(bitmap);
                break;
            case R.id.Button3:  //条形二维码

                MyBarCode.builder("https://www.baidu.com/").
                        backColor(getResources().getColor(R.color.transparent)).
                        codeColor(getResources().getColor(R.color.black)).
                        codeWidth(1000).
                        codeHeight(300).
                        into(mImage1);

                //条形码生成方式二  默认宽为1000 高为300 背景为白色 二维码为黑色
                //mIvBarCode.setImageBitmap(RxBarCode.createBarCode(str1, 1000, 300));
                break;
        }
    }
}
