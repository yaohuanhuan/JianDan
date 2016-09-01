package com.yx.jiandan.ui.base;

import android.app.Application;

import com.yx.jiandan.db.base.AbstractDatabaseManager;
import com.yx.jiandan.ui.imageload.ImageLoadProxy;

/**
 * Created by Y on 2016/8/11.
 */
public class JDApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AbstractDatabaseManager.initOpenHelper(getApplicationContext());//初始化数据库
        ImageLoadProxy.initImageLoader(getApplicationContext());       //初始化图片加载的代理

    }
}
