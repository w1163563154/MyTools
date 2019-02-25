package com.xiaoyu.mytools.Ui.Activity.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoyu.mytools.Base.MyRecyclerViewAdapter;
import com.xiaoyu.mytools.Info.MainInfo;
import com.xiaoyu.mytools.OnClickUtils.MyOnClickLisenter;
import com.xiaoyu.mytools.R;

import java.util.List;

/**
 * Created by yfb on 2019/1/23.
 */

public class MainAdapter extends MyRecyclerViewAdapter<MainInfo> {
    private MyOnClickLisenter mListener;
    public Context mContext;
    public MainAdapter(Context context, List<MainInfo> list, int layoutId ) {
        super(context, list, layoutId);
        this.mContext=context;
    }

    @Override
    protected void onBind(MyViewHolder holder, final MainInfo mainInfo, int position) {
        TextView name = holder.getView(R.id.item_mian_rlv);
        LinearLayout root = holder.getView(R.id.item_mian_root);
        name.setText(mainInfo.getName());


       holder.setOnClickListener(R.id.item_mian_root, new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Activity activity = mainInfo.getActivity();
               Log.e("data",activity.toString()+"");
               mContext.startActivity(new Intent(mContext, activity.getClass()));
           }
       });

    }




}
