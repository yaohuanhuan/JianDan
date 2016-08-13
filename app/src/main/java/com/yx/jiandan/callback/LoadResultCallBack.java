package com.yx.jiandan.callback;

/**
 * Created by Y on 2016/8/13.
 * 网络请求结果的回调
 */
public interface LoadResultCallBack<T> {

    int SUCCESS_OK = 1001;
    int SUCCESS_NONE = 1002;
    int ERROR_NET = 1003;

    void onSuccess(int result, T t);

    void onError(int code, String msg);

}
