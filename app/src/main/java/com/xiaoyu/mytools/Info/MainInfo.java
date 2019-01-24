package com.xiaoyu.mytools.Info;

import android.app.Activity;

/**
 * Created by yfb on 2019/1/23.
 */

public class MainInfo {
    private String name;
    private Activity mActivity;
    public MainInfo(String name, Activity activity){
        this.name = name;
        this.mActivity=activity;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }
}
