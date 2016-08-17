package com.yx.jiandan.okhttp.parser;

import com.yx.jiandan.bean.FreshNew;
import com.yx.jiandan.okhttp.OkHttpBaseParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Response;

/**
 * Created by Y on 2016/8/17.
 */
public class FreshNewsParser extends OkHttpBaseParser<ArrayList<FreshNew>> {

    @Override
    public ArrayList<FreshNew> parse(Response response) {

        code = wrapperCode(response.code());
        if (!response.isSuccessful()){
            return null;
        }
        try {
            String body = response.body().string();
            JSONObject resultObj = new JSONObject(body);
            JSONArray postsArray = resultObj.optJSONArray("posts");
            return FreshNew.parse(postsArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
