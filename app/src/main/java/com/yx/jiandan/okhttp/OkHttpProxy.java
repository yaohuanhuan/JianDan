package com.yx.jiandan.okhttp;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Y on 2016/8/16.
 * OkHttp的简单封装
 */
public class OkHttpProxy {

    /**
     * 单例模式
     */
    private static OkHttpClient mHttpClient;

    public static void init(){
        synchronized (OkHttpProxy.class){
            if (mHttpClient == null) {
                mHttpClient = new OkHttpClient();
                mHttpClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
                mHttpClient.newBuilder().readTimeout(15, TimeUnit.SECONDS);
                mHttpClient.newBuilder().writeTimeout(15, TimeUnit.SECONDS);

            }
        }
    }

    public static OkHttpClient getInstance() {
        if (mHttpClient == null)
            init();
        return mHttpClient;
    }

    public static Call get(String url, OkHttpCallback responseCallback) {
        return get(url, null, responseCallback);
    }

    public static Call get(String url, Object tag, OkHttpCallback responseCallback) {
        Request.Builder builder = new Request.Builder().url(url);
        if (tag != null) {
            builder.tag(tag);
        }
        Request request = builder.build();
        Call call = getInstance().newCall(request);
        call.enqueue(responseCallback);
        return call;
    }

    public static Call post(String url, Map<String, String> params, OkHttpCallback responseCallback) {
        return post(url, params, null, responseCallback);
    }

    public static Call post(String url, Map<String, String> params, Object tag, OkHttpCallback responseCallback) {

        Request.Builder builder = new Request.Builder().url(url);
        if (tag != null) {
            builder.tag(tag);
        }

        FormBody.Builder formBody = new FormBody.Builder();

        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                formBody.add(key, params.get(key));
            }
        }
        builder.post(formBody.build());

        Request request = builder.build();
        Call call = getInstance().newCall(request);
        call.enqueue(responseCallback);
        return call;
    }

//    public static void cancel(Object tag) {
//        getInstance().cancel(tag);
//    }


}
