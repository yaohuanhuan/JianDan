package com.yx.jiandan.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Y on 2016/9/20.
 */
public class Picture  implements Serializable{

    private int current_page;
    private JSONArray comments;

    public Picture(JSONObject jsonObject){
        this.current_page = jsonObject.optInt("current_page");
        this.comments = jsonObject.optJSONArray("comments");
    }

    public JSONArray getComments() {
        return comments;
    }

    public void setComments(JSONArray comments) {
        this.comments = comments;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }



}
