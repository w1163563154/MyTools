package com.xiaoyu.mytools.Ui.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoyu.mytools.Base.MyActivity;
import com.xiaoyu.mytools.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by yfb on 2019/2/18.
 */

public class TextActivity extends MyActivity {
    @BindView(R.id.text)
    TextView mText;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.stu)
    ViewStub stu;

    private boolean isstu=false;
    @Override
    public int setLayout() {
        return R.layout.activity_text;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);


        //创建一个上游 Observable：
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("data", "subscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d("data", "" + value);
                mText.setText(value + "");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("data", e.toString());
            }

            @Override
            public void onComplete() {
                Log.d("data", "complete");
            }
        };
        //建立连接
        observable.subscribe(observer);
    }



    @OnClick({R.id.button, R.id.text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                isstu=!isstu;
                if(isstu){
                    stu.inflate();
                }else{

                stu.setVisibility(View.GONE);
            }

                break;
            case R.id.text:
                break;
        }
    }
}
