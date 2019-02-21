package com.xiaoyu.mytools.Ui.Activity.DialogActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.xiaoyu.mytools.Base.MyActivity;
import com.xiaoyu.mytools.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yfb on 2019/2/21.
 */

public class DialogActivity extends MyActivity {
    @BindView(R.id.smscode)
    Button mSmscode;
    @BindView(R.id.bottom)
    Button mBottom;

    @Override
    public int setLayout() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }



    @OnClick({R.id.smscode, R.id.bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.smscode:
                final DialogEdit dialogEdit = new DialogEdit(DialogActivity.this, R.style.tran_dialog); //背景不变暗
                //final DialogEdit dialogEdit = new DialogEdit(DialogActivity.this); //背景变暗
                //final DialogEdit dialogEdit = new DialogEdit(DialogActivity.this,0.5f,Gravity.BOTTOM); //背景变暗全变半透明 底部出现
                dialogEdit.getTitleView().setBackgroundResource(R.drawable.logo);
                dialogEdit.getSureView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogEdit.dismiss();
                    }
                });

                dialogEdit.getCancelView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogEdit.dismiss();
                    }
                });
                dialogEdit.show();
                break;
            case R.id.bottom:
                break;
        }
    }
}
