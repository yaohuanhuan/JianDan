package com.yx.jiandan.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.yx.jiandan.gen.DaoSession;
import com.yx.jiandan.gen.FreshNewsDao;
import com.yx.jiandan.gen.AuthorDao;
import com.yx.jiandan.gen.CustomFieldsDao;
import com.yx.jiandan.gen.TagsDao;

/**
 * 新鲜事
 * Created by zhaokaiqiang on 15/4/24.
 */
@Entity
public class FreshNews implements Serializable {

    public static final String URL_FRESH_NEWS = "http://jandan.net/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,comment_count,custom_fields&custom_fields=thumb_c,views&dev=1&page=";

    public static final String URL_FRESH_NEWS_DETAIL = "http://i.jandan.net/?oxwlxojflwblxbsapi=get_post&include=content&id=";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    @Id
    public Long id;
    //文章id
    private String mId;
    //文章标题
    private String title;
    //文章地址
    private String url;
    //发布日期
    private String date;
    //缩略图
    private String thumb_c;
    //评论数
    private String comment_count;
    //作者
    @ToOne(joinProperty = "id")
    private Author author;
    //自定义字段
    @ToOne(joinProperty = "id")
    private CustomFields custom_fields;
    //标签
    @ToOne(joinProperty = "id")
    private Tags tags;

    @Generated(hash = 1393763894)
    private transient Long tags__resolvedKey;

    @Generated(hash = 1863990273)
    private transient Long custom_fields__resolvedKey;

    @Generated(hash = 1107320010)
    private transient Long author__resolvedKey;

    /** Used for active entity operations. */
    @Generated(hash = 1136505932)
    private transient FreshNewsDao myDao;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public FreshNews() {
    }

    @Generated(hash = 1475155560)
    public FreshNews(Long id, String mId, String title, String url, String date, String thumb_c, String comment_count) {
        this.id = id;
        this.mId = mId;
        this.title = title;
        this.url = url;
        this.date = date;
        this.thumb_c = thumb_c;
        this.comment_count = comment_count;
    }

    public static String getUrlFreshNews(int page) {
        return URL_FRESH_NEWS + page;
    }

    public static String getUrlFreshNewsDetail(String id) {
        return URL_FRESH_NEWS_DETAIL + id;
    }


    public static ArrayList<FreshNews> parse(JSONArray postsArray) {

        ArrayList<FreshNews> freshNewses = new ArrayList<>();

        for (int i = 0; i < postsArray.length(); i++) {

            FreshNews freshNews = new FreshNews();
            JSONObject jsonObject = postsArray.optJSONObject(i);

            freshNews.setmId(jsonObject.optString("id"));
            freshNews.setUrl(jsonObject.optString("url"));
            freshNews.setTitle(jsonObject.optString("title"));
            freshNews.setDate(jsonObject.optString("date"));
            freshNews.setComment_count(jsonObject.optString("comment_count"));
            freshNews.setAuthor(Author.parse(jsonObject.optJSONObject("author")));
            freshNews.setCustomFields(CustomFields.parse(jsonObject.optJSONObject("custom_fields")));
            freshNews.setTags(Tags.parse(jsonObject.optJSONArray("tags")));

            freshNewses.add(freshNews);

        }
        return freshNewses;
    }


    public static ArrayList<FreshNews> parseCache(JSONArray postsArray) {

        ArrayList<FreshNews> freshNewses = new ArrayList<>();

        for (int i = 0; i < postsArray.length(); i++) {

            FreshNews freshNews = new FreshNews();
            JSONObject jsonObject = postsArray.optJSONObject(i);

            freshNews.setmId(jsonObject.optString("id"));
            freshNews.setUrl(jsonObject.optString("url"));
            freshNews.setTitle(jsonObject.optString("title"));
            freshNews.setDate(jsonObject.optString("date"));
            freshNews.setComment_count(jsonObject.optString("comment_count"));
            freshNews.setAuthor(Author.parse(jsonObject.optJSONObject("author")));
            freshNews.setCustomFields(CustomFields.parseCache(jsonObject.optJSONObject("custom_fields")));
            freshNews.setTags(Tags.parseCache(jsonObject.optJSONObject("tags")));

            freshNewses.add(freshNews);

        }
        return freshNewses;
    }

    @Override
    public String toString() {
        return "FreshNews{" +
                "tags=" + tags +
                ", customFields=" + custom_fields +
                ", author=" + author +
                ", comment_count='" + comment_count + '\'' +
                ", thumb_c='" + thumb_c + '\'' +
                ", date='" + date + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", id='" + mId + '\'' +
                '}';
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }
    @Keep
    public Author getAuthor() {
        return author;
    }
    @Keep
    public void setAuthor(Author author) {
        this.author = author;
    }
    @Keep
    public CustomFields getCustomFields() {
        return custom_fields;
    }
    @Keep
    public void setCustomFields(CustomFields customFields) {
        this.custom_fields = customFields;
    }
    @Keep
    public Tags getTags() {
        return tags;
    }
    @Keep
    public void setTags(Tags tags) {
        this.tags = tags;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 911692897)
    public void setCustom_fields(CustomFields custom_fields) {
        synchronized (this) {
            this.custom_fields = custom_fields;
            id = custom_fields == null ? null : custom_fields.getId();
            custom_fields__resolvedKey = id;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 518982979)
    public CustomFields getCustom_fields() {
        Long __key = this.id;
        if (custom_fields__resolvedKey == null || !custom_fields__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CustomFieldsDao targetDao = daoSession.getCustomFieldsDao();
            CustomFields custom_fieldsNew = targetDao.load(__key);
            synchronized (this) {
                custom_fields = custom_fieldsNew;
                custom_fields__resolvedKey = __key;
            }
        }
        return custom_fields;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1956682582)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFreshNewsDao() : null;
    }

    public String getMId() {
        return this.mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }


}
