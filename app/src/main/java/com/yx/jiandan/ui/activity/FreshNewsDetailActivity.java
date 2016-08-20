package com.yx.jiandan.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yx.jiandan.R;
import com.yx.jiandan.ui.base.BaseActivity;

/**
 * Created by Y on 2016/8/20.
 */
public class FreshNewsDetailActivity extends BaseActivity {

    private ViewPager vp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_news_detail);

        initView();
        initData();
    }

    @Override
    protected void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
    }

    @Override
    protected void initData() {

    }

    private class FreshNewsDetailAdapter extends FragmentPagerAdapter {

        public FreshNewsDetailAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }

}
