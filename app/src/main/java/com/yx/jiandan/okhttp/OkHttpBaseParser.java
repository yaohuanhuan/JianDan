package com.yx.jiandan.okhttp;

import android.support.annotation.Nullable;

import okhttp3.Response;

/**
 * Created by Y on 2016/8/17.
 * 负责对返回值进行解析，使用的是策略设计模式
 */
public abstract class OkHttpBaseParser<T> {

    public int code;

    public abstract T parse(Response response);

    /**
     * 对返回码进行包装，可以自定义返回值,返回值写在OkHttpCallback里
     * @param code
     * @return
     */

    protected static int wrapperCode(int code) {
        if (code >= 500) {
            return OkHttpCallback.ERROR_SERVER;
        } else if (code >= 400) {
            return OkHttpCallback.ERROR_CLIENT;
        } else {
            return OkHttpCallback.SUCCESS_OK;
        }
    }

}
