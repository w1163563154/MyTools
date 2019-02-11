package com.xiaoyu.mytools.Utils;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoyu.mytools.Globlo.MyApplication;
import com.xiaoyu.mytools.R;


/**
 * Toast集合
 * Created by yfb on 2018/11/30.
 */

public class T {


    private static Toast mToast=null;
    /**
     * 显示自定义Toast (不弹出多个)
     *
     * @param text
     */
    public static void showShort(String text){
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.toast,null);
        view.getBackground().setAlpha(180);
        if(mToast==null){
            mToast = new Toast(MyApplication.getContext());
            mToast.setView(view);
        }else{
            mToast.setView(view);
        }
        TextView tv = (TextView) view.findViewById(R.id.toast_txt);
        tv.setText(text);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER,0,0);
        mToast.show();
    }

    /**
     * 普通Toast
     */

    public static void showShortToast(String text){
        Toast.makeText(MyApplication.getContext(),text,Toast.LENGTH_SHORT).show();
    }

}
