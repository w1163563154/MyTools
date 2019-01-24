package com.xiaoyu.mytools.Ui.Activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.xiaoyu.mytools.Base.MyActivity;
import com.xiaoyu.mytools.Info.MainInfo;
import com.xiaoyu.mytools.OnClickUtils.MyOnClickLisenter;
import com.xiaoyu.mytools.R;
import com.xiaoyu.mytools.Ui.Activity.Adapter.MainAdapter;
import com.xiaoyu.mytools.Ui.Activity.DianZhanActivity.DianZhanActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MyActivity {

    @BindView(R.id.Main_rlv)
    RecyclerView mMainRlv;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        ArrayList<MainInfo> nameList = new ArrayList<MainInfo>();
        nameList.add(new MainInfo("点赞",new DianZhanActivity()));
        nameList.add(new MainInfo("壁纸",new MainActivity()));
        nameList.add(new MainInfo("号的",new DianZhanActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mMainRlv.setLayoutManager(gridLayoutManager);
        MainAdapter mainAdapter = new MainAdapter(this, nameList, R.layout.recyclerview_main);
        mMainRlv.setAdapter(mainAdapter);

    }




}
