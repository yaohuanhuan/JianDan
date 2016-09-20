package com.yx.jiandan.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yx.jiandan.R;
import com.yx.jiandan.model.FreshNews;
import com.yx.jiandan.ui.base.BaseActivity;
import com.yx.jiandan.ui.fragment.FreshNewsDetailFragment;

import java.util.ArrayList;

/**
 * Created by Y on 2016/8/20.
 */
public class FreshNewsDetailActivity extends BaseActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_news_detail);

        initView();
        initData();
    }

    @Override
    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp);
    }

    @Override
    protected void initData() {
        ArrayList<FreshNews> FreshNews = (ArrayList<com.yx.jiandan.model.FreshNews>) getIntent().getSerializableExtra(DATA_FRESH_NEWS);
        int position = getIntent().getIntExtra(DATA_POSITION,0);
        viewPager.setAdapter(new FreshNewsDetailAdapter(getSupportFragmentManager(), FreshNews));
        viewPager.setCurrentItem(position);

    }

    private class FreshNewsDetailAdapter extends FragmentPagerAdapter {

        private ArrayList<FreshNews> freshNewses;

        public FreshNewsDetailAdapter(FragmentManager fm, ArrayList<FreshNews> freshNewses) {
            super(fm);
            this.freshNewses = freshNewses;
        }

        @Override
        public Fragment getItem(int position) {
            return FreshNewsDetailFragment.getInstance(freshNewses.get(position));
        }

        @Override
        public int getCount() {
            return freshNewses.size();
        }
    }

}
