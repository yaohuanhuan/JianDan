package com.yx.jiandan.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.yx.jiandan.R;
import com.yx.jiandan.ui.fragment.FreshNewsFragment;
import com.yx.jiandan.ui.fragment.JokeFragment;
import com.yx.jiandan.ui.fragment.PictureFragment;
import com.yx.jiandan.ui.fragment.SettingFragment;
import com.yx.jiandan.ui.fragment.SisterFragment;
import com.yx.jiandan.ui.fragment.VideoFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FreshNewsFragment freshNewsFragment;
    private JokeFragment jokeFragment;
    private PictureFragment pictureFragment;
    private SettingFragment settingFragment;
    private SisterFragment sisterFragment;
    private VideoFragment videoFragment;

    private FragmentManager fragmentManager;

    private RelativeLayout rl_1,rl_2,rl_3,rl_4,rl_5,rl_6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        bindViews();
        rl_1.performClick();

    }

    /**
     * UI组件初始化与事件绑定
     */

    private void bindViews() {

        rl_1 = (RelativeLayout) findViewById(R.id.rl_1);
        rl_2 = (RelativeLayout) findViewById(R.id.rl_2);
        rl_3 = (RelativeLayout) findViewById(R.id.rl_3);
        rl_4 = (RelativeLayout) findViewById(R.id.rl_4);
        rl_5 = (RelativeLayout) findViewById(R.id.rl_5);
//        rl_6 = (RelativeLayout) findViewById(R.id.rl_6);

        rl_1.setOnClickListener(this);
        rl_2.setOnClickListener(this);
        rl_3.setOnClickListener(this);
        rl_4.setOnClickListener(this);
        rl_5.setOnClickListener(this);
//        rl_6.setOnClickListener(this);
    }

    /**
     * 重置所有文本的选中状态
     */

    private void setSelected() {
        rl_1.setSelected(false);
        rl_2.setSelected(false);
        rl_3.setSelected(false);
        rl_4.setSelected(false);
        rl_5.setSelected(false);
//        rl_6.setSelected(false);
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (freshNewsFragment != null) fragmentTransaction.hide(freshNewsFragment);
        if (jokeFragment != null) fragmentTransaction.hide(jokeFragment);
        if (pictureFragment != null) fragmentTransaction.hide(pictureFragment);
        if (settingFragment != null) fragmentTransaction.hide(settingFragment);
        if (sisterFragment != null) fragmentTransaction.hide(sisterFragment);
        if (videoFragment != null) fragmentTransaction.hide(videoFragment);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (view.getId()) {
            case R.id.rl_1:
                setSelected();
                rl_1.setSelected(true);
                if (freshNewsFragment == null) {
                    freshNewsFragment = new FreshNewsFragment();
                    fTransaction.add(R.id.fragment_container, freshNewsFragment);
                } else {
                    fTransaction.show(freshNewsFragment);
                }
                break;
            case R.id.rl_2:
                setSelected();
                rl_2.setSelected(true);
                if (pictureFragment == null) {
                    pictureFragment = new PictureFragment();
                    fTransaction.add(R.id.fragment_container, pictureFragment);
                } else {
                    fTransaction.show(pictureFragment);
                }
                break;


            case R.id.rl_3:
                setSelected();
                rl_3.setSelected(true);
                if (sisterFragment == null) {
                    sisterFragment = new SisterFragment();
                    fTransaction.add(R.id.fragment_container, sisterFragment);
                } else {
                    fTransaction.show(sisterFragment);
                }
                break;
            case R.id.rl_4:
                setSelected();
                rl_4.setSelected(true);
                if (jokeFragment == null) {
                    jokeFragment = new JokeFragment();
                    fTransaction.add(R.id.fragment_container, jokeFragment);
                } else {
                    fTransaction.show(jokeFragment);
                }
                break;
            case R.id.rl_5:
                setSelected();
                rl_5.setSelected(true);
                if (videoFragment == null) {
                    videoFragment = new VideoFragment();
                    fTransaction.add(R.id.fragment_container, videoFragment);
                } else {
                    fTransaction.show(videoFragment);
                }
                break;
//            case R.id.rl_6:
//                setSelected();
//                rl_5.setSelected(true);
//                if (settingFragment == null) {
//                    settingFragment = new SettingFragment();
//                    fTransaction.add(R.id.fragment_container, settingFragment);
//                } else {
//                    fTransaction.show(settingFragment);
//                }
//                break;
        }
        fTransaction.commit();
    }

}
