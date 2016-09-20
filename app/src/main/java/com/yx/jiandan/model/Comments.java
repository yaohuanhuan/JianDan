package com.yx.jiandan.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Y on 2016/9/20.
 */
public class Comments implements Serializable {

    private String comment_date;
    private JSONArray pics;

    public Comments(JSONObject jsonObject){
        this.comment_date = jsonObject.optString("comment_date");
        this.pics = jsonObject.optJSONArray("pics");
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public JSONArray getPics() {
        return pics;
    }

    public void setPics(JSONArray pics) {
        this.pics = pics;
    }
}
