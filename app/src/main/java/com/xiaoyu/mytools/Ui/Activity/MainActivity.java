package com.xiaoyu.mytools.Ui.Activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.xiaoyu.mytools.Base.MyActivity;
import com.xiaoyu.mytools.Info.MainInfo;
import com.xiaoyu.mytools.OnClickUtils.MyOnClickLisenter;
import com.xiaoyu.mytools.R;
import com.xiaoyu.mytools.Ui.Activity.Adapter.MainAdapter;
import com.xiaoyu.mytools.Ui.Activity.DianZhanActivity.DianZhanActivity;
import com.xiaoyu.mytools.Ui.Activity.PullZoomActivity.PullScrollViewActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MyActivity {

    @BindView(R.id.Main_rlv)
    RecyclerView mMainRlv;
    private ArrayList<MainInfo> mNameList;
    private MainAdapter mMainAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mMainRlv.setLayoutManager(gridLayoutManager);
        mMainRlv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mMainRlv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        mMainAdapter = new MainAdapter(this, mNameList, R.layout.recyclerview_main);
        mMainRlv.setAdapter(mMainAdapter);
    }

    @Override
    protected void initData() {
        mNameList = new ArrayList<MainInfo>();
        mNameList.add(new MainInfo("点赞",new DianZhanActivity()));
        mNameList.add(new MainInfo("更换壁纸与表格控件",new BiZhiActivity()));
        mNameList.add(new MainInfo("表格控件",new BiZhiActivity()));
        mNameList.add(new MainInfo("放大",new PullScrollViewActivity()));
        mNameList.add(new MainInfo("平滑",new DianZhanActivity()));
        mMainAdapter.refresh(mNameList);

    }
}
