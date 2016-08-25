package com.yx.jiandan.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.json.JSONObject;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class Author implements Serializable {
    @Id
    public Long id;
    public String mId;
    public String slug;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String name;
    public String first_name;
    public String last_name;
    public String nickname;
    public String url;
    public String description;

    @Generated(hash = 1493658521)
    public Author(Long id, String mId, String slug, String name, String first_name,
            String last_name, String nickname, String url, String description) {
        this.id = id;
        this.mId = mId;
        this.slug = slug;
        this.name = name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.nickname = nickname;
        this.url = url;
        this.description = description;
    }

    @Generated(hash = 64241762)
    public Author() {
    }

    public static Author parse(final JSONObject jsonObject) {
        Author author;
        if (jsonObject == null) {
            author = null;
        } else {
            author = new Author();
            author.mId = jsonObject.optString("id");
            author.slug = jsonObject.optString("slug");
            author.name = jsonObject.optString("name");
            author.first_name = jsonObject.optString("first_name");
            author.last_name = jsonObject.optString("last_name");
            author.nickname = jsonObject.optString("nickname");
            author.url = jsonObject.optString("url");
            author.description = jsonObject.optString("description");
        }
        return author;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getMId() {
        return this.mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }
}
