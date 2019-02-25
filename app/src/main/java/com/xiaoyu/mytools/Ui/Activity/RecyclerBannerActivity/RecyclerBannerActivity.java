package com.xiaoyu.mytools.Ui.Activity.RecyclerBannerActivity;


import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import com.xiaoyu.mytools.Base.MyActivity;
import com.xiaoyu.mytools.R;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by yfb on 2019/2/12.
 */

public class RecyclerBannerActivity extends MyActivity implements BannerLayout.OnBannerItemClickListener {

    RecyclerView recyclerView;
    OverFlyingLayoutManager mOverFlyingLayoutManager;
    Handler mHandler;
    Runnable mRunnable;
    int currentPosition = 0;
    @Override
    public int setLayout() {
        return R.layout.activity_recyclerbanner;
    }

    @Override
    protected void initView() {

        BannerLayout  recyclerBanner =  findViewById(R.id.recycler);
        BannerLayout bannerVertical =  findViewById(R.id.recycler_ver);
        List<String> list = new ArrayList<>();
        list.add("http://img0.imgtn.bdimg.com/it/u=1352823040,1166166164&fm=27&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=2293177440,3125900197&fm=27&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=3967183915,4078698000&fm=27&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=3184221534,2238244948&fm=27&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=1794621527,1964098559&fm=27&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=1243617734,335916716&fm=27&gp=0.jpg");
        WebBannerAdapter webBannerAdapter=new WebBannerAdapter(this,list);
        webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(RecyclerBannerActivity.this, "点击了第  " + position+"  项", Toast.LENGTH_SHORT).show();
            }
        });

        WebBannerAdapter WebBannerAdapter2 =new WebBannerAdapter(this,list);
        WebBannerAdapter2.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(RecyclerBannerActivity.this, "点击了第  " + position+"  项", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerBanner.setAdapter(webBannerAdapter);
        bannerVertical.setAdapter(WebBannerAdapter2);

//<-------------------------------------------------------------------------------------------------------->

        recyclerView = (RecyclerView) findViewById(R.id.recycler_banner);
        mOverFlyingLayoutManager = new OverFlyingLayoutManager(0.75f, 50, OverFlyingLayoutManager.HORIZONTAL);

        recyclerView.setAdapter(new LocalDataAdapter());
        recyclerView.setLayoutManager(mOverFlyingLayoutManager);

        recyclerView.addOnScrollListener(new CenterScrollListener());
        mOverFlyingLayoutManager.setOnPageChangeListener(new OverFlyingLayoutManager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                currentPosition++;
                Log.d("recyclerBanner", currentPosition + " ");
                mOverFlyingLayoutManager.scrollToPosition(currentPosition);
                //  recyclerView.smoothScrollToPosition(currentPosition);
                mHandler.postDelayed(this, 3000);
            }
        };
        mHandler.postDelayed(mRunnable, 3000);

        SeekBar seekBar = (SeekBar) findViewById(R.id.progress);
        seekBar.setProgress(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mOverFlyingLayoutManager.setItemSpace(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }





    @Override
    public void onItemClick(int position) {

    }
}
