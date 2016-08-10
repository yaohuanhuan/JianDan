package com.yx.jiandan.bean;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Y on 2016/8/10.
 */
public class FreshNews {



    public JSONArray posts;

    public FreshNews(JSONObject jsonObject){

        posts = jsonObject.optJSONArray("posts");

    }

    public JSONArray getPosts() {
        return posts;
    }

    public void setPosts(JSONArray posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "FreshNews{" +
                "posts=" + posts +
                '}';
    }

}
