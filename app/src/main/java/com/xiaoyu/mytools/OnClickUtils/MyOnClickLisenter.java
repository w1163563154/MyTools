package com.xiaoyu.mytools.OnClickUtils;

import android.view.View;

import com.xiaoyu.mytools.R;

/**
 * Created by yfb on 2019/1/23.
 */

public abstract class MyOnClickLisenter implements View.OnClickListener{
    @Override
    public void onClick(View view) {
        MyOnClick((Integer) view.getTag(R.id.tag_first),view);
    }

    protected abstract void MyOnClick(int position,View v);

}
