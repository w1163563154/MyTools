package com.xiaoyu.mytools.Ui.Activity;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;

import android.view.View;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.table.TableData;
import com.xiaoyu.mytools.Base.MyActivity;
import com.xiaoyu.mytools.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yfb on 2019/1/26.
 */

public class BiZhiActivity extends MyActivity {

    private SmartTable table;

    private WallpaperManager wManager = null;   //定义WallpaperManager服务
    @Override
    public int setLayout() {

        /**
         * 表格控件
         * compile 'com.github.huangyanbin:SmartTable:2.0'  （添加依赖）
         */

        return R.layout.activity_bizhi;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

       //-------------------------------表格控件-----------------------------------
        table = findViewById(R.id.table);
        List<User> list = new ArrayList<>();
        list.add(new User("沈阳1",100));
        list.add(new User("沈阳2",101));
        Column<String> name = new Column<>("姓名", "name");
        Column<Integer> age = new Column<>("年龄", "age");
        TableData<User> tableData = new TableData<>("姓名表", list, name, age);
        table.setTableData(tableData);
        //-------------------------------表格控件-----------------------------------
    }

    public class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }



    @SuppressLint("ResourceType")
    @OnClick({R.id.change, R.id.cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                //修改壁纸
                wManager = WallpaperManager.getInstance(BiZhiActivity.this);

                try {
                    wManager.setResource(R.mipmap.fangda);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.cancel:
                //清楚壁纸
                try {
                    WallpaperManager.getInstance(getApplicationContext()).clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
