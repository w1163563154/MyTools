package com.xiaoyu.mytools.Globlo;

import android.app.Application;
import android.content.Context;

/**
 * Created by yfb on 2019/1/24.
 */

public class MyApplication extends Application {

    public static Context mContext=null;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
    }


    public static Context getContext(){
        return mContext;
    }
}
