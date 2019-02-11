package com.xiaoyu.mytools.Ui.Activity;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.xiaoyu.mytools.Base.MyActivity;
import com.xiaoyu.mytools.R;

import java.util.Date;
import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yfb on 2019/1/29.
 */

public class ShiJianActivity extends MyActivity {
    @BindView(R.id.button)
    Button mButton;
   private TimePickerBuilder pvTime;
    @Override
    public int setLayout() {
        return R.layout.activity_shijian;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.button)
    public void onViewClicked(View view) {
        switch (view.getId()){



            //添加依赖  compile 'com.contrarywind:Android-PickerView:4.1.7'
            case R.id.button:
                Calendar selectedDate = Calendar.getInstance();
                Calendar startDate = Calendar.getInstance();
                //startDate.set(2013,1,1);
                Calendar endDate = Calendar.getInstance();
                //endDate.set(2020,1,1);
                int year = startDate.get(Calendar.YEAR);
                int month = startDate.get(Calendar.MONTH);
                int data = startDate.get(Calendar.DATE);
                //正确设置方式 原因：注意事项有说明
               // startDate.set(2013,0,1);
                startDate.set(year,month,data);

              /*  int date=25;
                int newmonth;
                int newyear;
                if(data+date>31){
                    data=data+25-31;
                    month=month+11;
                    if(month>12){
                        month=month+1-month;
                        year=year+1;
                    }
                }else{
                    data=date+data;
                }*/
                //endDate.set(year,month,data);
                endDate.set(2020,11,5);

                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(ShiJianActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        Toast.makeText(ShiJianActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
                    }
                }).setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                        .setRangDate(startDate,endDate)//起始终止年月日设定
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("Title")//标题文字
                        .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                        .isCyclic(false)//是否循环滚动
                        .setTitleColor(Color.BLACK)//标题文字颜色
                        .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                        .setCancelColor(Color.BLUE)//取消按钮文字颜色
                        .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
                        .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                        .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .isDialog(true)//是否显示为对话框样式
                        .build();
                pvTime.show();
                break;
        }
    }
}
