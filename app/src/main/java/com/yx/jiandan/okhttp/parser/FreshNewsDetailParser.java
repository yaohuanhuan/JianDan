package com.yx.jiandan.okhttp.parser;

import android.support.annotation.Nullable;


import com.yx.jiandan.okhttp.OkHttpBaseParser;

import org.json.JSONObject;

import okhttp3.Response;

/**
 * Created by zhaokaiqiang on 15/11/22.
 */
public class FreshNewsDetailParser extends OkHttpBaseParser<String> {

    @Nullable
    public String parse(Response response) {

        code = wrapperCode(response.code());
        if (!response.isSuccessful())
            return null;

        try {
            JSONObject jsonObject = new JSONObject(response.body().string());
            if (jsonObject.opt("status").equals("ok")) {
                JSONObject contentObject = jsonObject.optJSONObject("post");
                return contentObject.optString("content");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
