package com.yx.jiandan.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
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
    public Long tagsId;
    private int id;
    private String title;
    private String description;

    @Generated(hash = 1634080722)
    public Tags(Long tagsId, int id, String title, String description) {
        this.tagsId = tagsId;
        this.id = id;
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
                tags.id = optJSONObject.optInt("id");
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
            tags.id = jsonObject.optInt("id");
            tags.title = jsonObject.optString("title");
            tags.description = jsonObject.optString("description");
        }
        return tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public void setTagsId(Long tagsId) {
        this.tagsId = tagsId;
    }

    public Long getTagsId() {
        return this.tagsId;
    }

}
