package com.xiaoyu.mytools.Ui.Activity;

import android.app.Activity;
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
import com.xiaoyu.mytools.Ui.Activity.DialogActivity.DialogActivity;
import com.xiaoyu.mytools.Ui.Activity.DianZhanActivity.DianZhanActivity;

import com.xiaoyu.mytools.Ui.Activity.DongGuangActivity.DongGuangActivity;
import com.xiaoyu.mytools.Ui.Activity.ErWeiActivity.ErWeiActivity;
import com.xiaoyu.mytools.Ui.Activity.PullZoomActivity.PullScrollViewActivity;
import com.xiaoyu.mytools.Ui.Activity.RecyclerBannerActivity.RecyclerBannerActivity;

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
        mNameList.add(new MainInfo("时间选择器",new ShiJianActivity()));
        mNameList.add(new MainInfo("下拉放大",new PullScrollViewActivity()));
        mNameList.add(new MainInfo("文字滚动",new WenZhiGunDongActivity()));
        mNameList.add(new MainInfo("RecyclerBanner",new RecyclerBannerActivity()));
        mNameList.add(new MainInfo("Dialog",new DialogActivity()));
        mNameList.add(new MainInfo("震动闪光",new DongGuangActivity()));
        mNameList.add(new MainInfo("二维码生成",new ErWeiActivity()));
        mNameList.add(new MainInfo("Text",new TextActivity()));
        mMainAdapter.refresh(mNameList);

    }
}
