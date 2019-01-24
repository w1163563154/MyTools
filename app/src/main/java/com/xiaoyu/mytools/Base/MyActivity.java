package com.xiaoyu.mytools.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yfb on 2019/1/23.
 */

public abstract class MyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();
        initData();
    }

    /**
     * 初始化布局
     * @return
     */
    public abstract int setLayout() ;

    /**
     * 初始化控件
     * @return
     */
    protected abstract void initView();

    /**
     * 初始化数据
     * @return
     */
    protected  void initData(){};





}
