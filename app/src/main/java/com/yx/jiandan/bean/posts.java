package com.yx.jiandan.bean;

import org.json.JSONObject;

/**
 * Created by Y on 2016/8/10.
 */
public class Posts {
    public int id;
    public String url;
    public String title;
    public String date;
    public String thumb_c;

    public Posts(JSONObject jsonObject){
        id = jsonObject.optInt("id");
        url = jsonObject.optString("url");
        title = jsonObject.optString("title");
        date = jsonObject.optString("date");
        thumb_c = jsonObject.optJSONObject("custom_fields").optJSONArray("thumb_c").optString(0);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getThumb_c() {
        return thumb_c;
    }

    public void setThumb_c(String thumb_c) {
        this.thumb_c = thumb_c;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", thumb_c='" + thumb_c + '\'' +
                '}';
    }
}
