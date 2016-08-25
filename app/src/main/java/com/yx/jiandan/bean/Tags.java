package com.yx.jiandan.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 新鲜事中的自定义字段
 */
@Entity
public class Tags implements Serializable {
    @Id
    public Long id;
    private int mId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    private String title;
    private String description;

    @Generated(hash = 388705158)
    public Tags(Long id, int mId, String title, String description) {
        this.id = id;
        this.mId = mId;
        this.title = title;
        this.description = description;
    }

    @Generated(hash = 1290390976)
    public Tags() {
    }

    public static Tags parse(final JSONArray jsonArray) {
        Tags tags;
        if (jsonArray == null) {
            tags = null;
        } else {
            tags = new Tags();
            JSONObject optJSONObject = jsonArray.optJSONObject(0);
            if (optJSONObject != null) {
                tags.mId = optJSONObject.optInt("id");
                tags.title = optJSONObject.optString("title");
                tags.description = optJSONObject.optString("description");
            }
        }
        return tags;
    }

    public static Tags parseCache(final JSONObject jsonObject) {
        Tags tags;
        if (jsonObject == null) {
            tags = null;
        } else {
            tags = new Tags();
            tags.mId = jsonObject.optInt("id");
            tags.title = jsonObject.optString("title");
            tags.description = jsonObject.optString("description");
        }
        return tags;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMId() {
        return this.mId;
    }

    public void setMId(int mId) {
        this.mId = mId;
    }



}
