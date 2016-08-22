package com.yx.jiandan.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.yx.jiandan.ui.imageload.ImageLoadProxy;

/**
 * Created by Y on 2016/8/22.
 */
public class BaseFragment extends Fragment implements ConstantString {

    private String TAG = "BaseFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"Fragment销毁，图片缓存清除");
        ImageLoadProxy.getImageLoader().clearMemoryCache();

    }
}
