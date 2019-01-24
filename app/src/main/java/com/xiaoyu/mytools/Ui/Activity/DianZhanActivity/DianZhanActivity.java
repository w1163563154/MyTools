package com.xiaoyu.mytools.Ui.Activity.DianZhanActivity;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoyu.mytools.Base.MyActivity;
import com.xiaoyu.mytools.R;

import java.io.IOException;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yfb on 2019/1/24.
 */

public class DianZhanActivity extends MyActivity {

    @BindView(R.id.po_image0)
    RxShineButton mRxShineButton;
    @BindView(R.id.po_image1)
    RxShineButton porterShapeImageView1;
    @BindView(R.id.po_image2)
    RxShineButton porterShapeImageView2;
    @BindView(R.id.po_image3)
    RxShineButton porterShapeImageView3;
    @BindView(R.id.ll_top)
    LinearLayout mLlTop;
    @BindView(R.id.wrapper)
    LinearLayout mWrapper;
    @BindView(R.id.po_image8)
    RxShineButton mPoImage8;
    @BindView(R.id.love)
    ImageView mLove;
    @BindView(R.id.ll_control)
    LinearLayout mLlControl;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;
    @BindView(R.id.heart_layout)
    RxHeartLayout mRxHeartLayout;
    @BindView(R.id.tv_hv)
    TextView mTvHv;
    @BindView(R.id.activity_like)
    RelativeLayout mActivityLike;

    @Override
    public int setLayout() {
        return R.layout.activity_dianzhan;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        mRxShineButton.init(this);
        porterShapeImageView1.init(this);
        porterShapeImageView2.init(this);
        porterShapeImageView3.init(this);

        RxShineButton rxShineButtonJava = new RxShineButton(this);

        rxShineButtonJava.setBtnColor(Color.GRAY);
        rxShineButtonJava.setBtnFillColor(Color.RED);
        rxShineButtonJava.setShapeResource(R.raw.heart);
        rxShineButtonJava.setAllowRandomColor(true);
        rxShineButtonJava.setShineSize(50);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
        rxShineButtonJava.setLayoutParams(layoutParams);
        if (mWrapper != null) {
            mWrapper.addView(rxShineButtonJava);
        }


        mRxShineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mRxShineButton.setOnCheckStateChangeListener(new RxShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
            }
        });

        porterShapeImageView2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {


            }
        });
        porterShapeImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


    }

    private Random random = new Random();

    @OnClick(R.id.love)
    public void onClick() {
        mRxHeartLayout.post(new Runnable() {
            @Override
            public void run() {
                int rgb = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
                mRxHeartLayout.addHeart(rgb);
            }
        });
    }
    }


