package com.xiaoyu.mytools.Ui.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.xiaoyu.mytools.Base.MyActivity;
import com.xiaoyu.mytools.Globlo.MyApplication;
import com.xiaoyu.mytools.R;
import com.xiaoyu.mytools.Utils.T;

import java.util.ArrayList;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import static com.xiaoyu.mytools.Globlo.MyApplication.getContext;
/**
 * Created by yfb on 2019/1/26.
 */

public class WenZhiGunDongActivity extends MyActivity {
    @BindView(R.id.follow_textSwitcher_up)
    TextSwitcher mTextSwitcherUp;
    public static final int NEWS_MESSAGE_TEXTVIEW=100;  //文字公告

    public int index=0;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case NEWS_MESSAGE_TEXTVIEW:
                    index++;
                    mTextSwitcherUp.setText(mStrings.get(index%mStrings.size()));
                    if (index == mStrings.size()) {
                        index = 0;
                    }
                    mHandler.sendEmptyMessageDelayed(NEWS_MESSAGE_TEXTVIEW,3000);
                    break;
            }
        }
    };
    private ArrayList<String> mStrings;

    @Override
    public int setLayout() {
        return R.layout.activity_wenzhi;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mStrings = new ArrayList<>();
        mStrings.add("我的剑，就是你的剑!");
        mStrings.add("俺也是从石头里蹦出来得!");
        mStrings.add("我用双手成就你的梦想");
        mStrings.add("人在塔在");
        mStrings.add("犯我德邦者");

        mTextSwitcherUp.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                final TextView tv = new TextView(getContext());
                //设置文字大小
                tv.setTextSize(15);
                //设置文字 颜色
                tv.setTextColor(Color.YELLOW);
                tv.setSingleLine();
                tv.setEllipsize(TextUtils.TruncateAt.END);
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.gravity = Gravity.CENTER;
                tv.setLayoutParams(lp);
                //内容的点击事件
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T.showShort(tv.getText().toString());
                    }
                });
                return tv;
            }
        });
        mHandler.sendEmptyMessage(NEWS_MESSAGE_TEXTVIEW);
    }



}
