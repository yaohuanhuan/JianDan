package com.yx.jiandan.okhttp.parser;

import com.yx.jiandan.bean.FreshNews;
import com.yx.jiandan.okhttp.OkHttpBaseParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Response;

/**
 * Created by Y on 2016/8/17.
 */
public class FreshNewsParser extends OkHttpBaseParser<ArrayList<FreshNews>> {

    @Override
    public ArrayList<FreshNews> parse(Response response) {

        code = wrapperCode(response.code());
        if (!response.isSuccessful()){
            return null;
        }
        try {
            String body = response.body().string();
            JSONObject resultObj = new JSONObject(body);
            JSONArray postsArray = resultObj.optJSONArray("posts");
            return FreshNews.parse(postsArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
