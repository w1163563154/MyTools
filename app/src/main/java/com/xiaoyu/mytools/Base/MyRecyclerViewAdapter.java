package com.xiaoyu.mytools.Base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoyu.mytools.R;

import java.util.List;

/**
 * Created by yfb on 2019/1/23.
 */

public abstract class MyRecyclerViewAdapter<E> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<E> mList;
    private Context mContext;
    private int mLayoutId;
    public MyRecyclerViewAdapter(Context context, List<E> list,int layoutId) {
        this.mList=list;
        this.mContext=context;
        this.mLayoutId=layoutId;
    }


    public void setData(List<E> list){
        this.mList=list;
    }

    public List<E> getData(){
        return this.mList;
    }

    //刷新Adapter
    public void refresh(List<E> list){
        this.mList=list;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        View itemView;
        SparseArray<View> views;//存放Item中的控件
        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            views=new SparseArray<View>();
        }

        //供adapter调用，返回holder
        public static <T extends MyViewHolder> T getHolder(Context cxt, ViewGroup parent, int layoutId){
            return (T) new MyViewHolder(LayoutInflater.from(cxt).inflate(layoutId, parent, false));
        }

        //根据Item中的控件Id获取控件
        public <T extends View> T getView(int viewId){
            View childreView = views.get(viewId);
            if(childreView==null){
                childreView=itemView.findViewById(viewId);
                views.put(viewId,childreView);
            }
            return (T) childreView;
        }

        //根据Item中的控件Id向控件添加事件监听
        public MyViewHolder setOnClickListener(int viewId, View.OnClickListener listener){
            View view = getView(viewId);
            view.setOnClickListener(listener);
            return this;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //从holder基类中获取holder
        return MyViewHolder.getHolder(mContext, parent, mLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //调用由子类实现的抽象方法，将操作下放到子类中
        onBind((MyViewHolder) holder, mList.get(position), position);
    }

    protected abstract void onBind(MyViewHolder holder, E e, int position);

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }



}
