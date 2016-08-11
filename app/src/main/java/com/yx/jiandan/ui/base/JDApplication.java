package com.yx.jiandan.ui.base;

import android.app.Application;

import com.yx.jiandan.ui.imageload.ImageLoadProxy;

/**
 * Created by Y on 2016/8/11.
 */
public class JDApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoadProxy.initImageLoader(this);       //初始化图片加载的代理

    }
}
