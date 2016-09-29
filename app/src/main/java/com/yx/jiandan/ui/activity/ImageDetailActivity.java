package com.yx.jiandan.ui.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.yx.jiandan.R;
import com.yx.jiandan.ui.base.BaseActivity;
import com.yx.jiandan.ui.imageload.ImageLoadProxy;

import java.io.File;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by yx on 2016/9/27.
 */

public class ImageDetailActivity extends BaseActivity{

    public static final int ANIMATION_DURATION = 400;

    public String url;
    public Boolean isNeedWebView;
    private boolean isBarShow = true;

    private PhotoView image;
    private ProgressBar progress;
    private RelativeLayout rl_top_bar;
    private LinearLayout ll_bottom_bar;
    private ImageButton img_back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        initView();
        initData();

    }

    @Override
    protected void initView() {
        image = (PhotoView) findViewById(R.id.img);
        progress = (ProgressBar) findViewById(R.id.progress);
        rl_top_bar = (RelativeLayout) findViewById(R.id.rl_top_bar);
        ll_bottom_bar = (LinearLayout) findViewById(R.id.ll_bottom_bar);
        img_back = (ImageButton) findViewById(R.id.img_back);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        url = intent.getStringExtra(DATA_IMAGE_URL);
        isNeedWebView = intent.getBooleanExtra(DATA_IS_NEED_WEBVIEW, false);
        ImageLoadProxy.displayImage4Detail(url,image,new SimpleImageLoadingListener(){

            @Override
            public void onLoadingStarted(String imageUri, View view) {
                super.onLoadingStarted(imageUri, view);
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                super.onLoadingFailed(imageUri, view, failReason);
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                progress.setVisibility(View.GONE);
            }
        });
        image.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                toggleBar();
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        toggleBar();
    }
    private void toggleBar() {
        if (true) {
            //隐藏
            if (isBarShow) {
                isBarShow = false;
                ObjectAnimator
                        .ofFloat(ll_bottom_bar, "translationY", 0, ll_bottom_bar.getHeight())
                        .setDuration(ANIMATION_DURATION)
                        .start();
                ObjectAnimator
                        .ofFloat(rl_top_bar, "translationY", 0, -rl_top_bar.getHeight())
                        .setDuration(ANIMATION_DURATION)
                        .start();
            } else {
                //显示
                isBarShow = true;
                ObjectAnimator
                        .ofFloat(ll_bottom_bar, "translationY", ll_bottom_bar.getHeight(), 0)
                        .setDuration(ANIMATION_DURATION)
                        .start();
                ObjectAnimator
                        .ofFloat(rl_top_bar, "translationY", -rl_top_bar.getHeight(), 0)
                        .setDuration(ANIMATION_DURATION)
                        .start();
            }
        }
    }
}
