package com.yx.jiandan.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yx.jiandan.R;
import com.yx.jiandan.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class PictureFragment extends Fragment {
    private TextView mText;
    private TextView mText2;
    private Button button;
    private Button button2;
    private CoordinatorLayout coordinatorLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
        mText = (TextView) getView().findViewById(R.id.tv_text);
        mText2 = (TextView) getView().findViewById(R.id.tv_text2);
        button = (Button) getView().findViewById(R.id.testButton);
        button2 = (Button) getView().findViewById(R.id.testButton2);
        coordinatorLayout = (CoordinatorLayout) getView().findViewById(R.id.CoordinatorLayout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MessageEvent("test"));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = 10;
                EventBus.getDefault().post(i);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(MessageEvent message){
        Snackbar.make(coordinatorLayout,"I am snackbar!",Snackbar.LENGTH_LONG).setAction("I am action!", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"我是吐司！",Toast.LENGTH_SHORT).show();
            }
        }).show();
        mText.setText(message.test);
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void helloEventBus(int message){
        mText2.setText("666666");
    }

}
