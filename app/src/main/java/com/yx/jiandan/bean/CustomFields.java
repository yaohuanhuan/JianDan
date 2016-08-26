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
public class CustomFields implements Serializable {
    @Id
    public Long CustomFieldsId;
    //自定义缩略图大小
    public String thumb_c;
    //中等缩略图大小
    public String thumb_m;
    //查看人数
    public String views;

    @Generated(hash = 540893866)
    public CustomFields(Long CustomFieldsId, String thumb_c, String thumb_m, String views) {
        this.CustomFieldsId = CustomFieldsId;
        this.thumb_c = thumb_c;
        this.thumb_m = thumb_m;
        this.views = views;
    }


    @Generated(hash = 1574734666)
    public CustomFields() {
    }

    public static CustomFields parse(final JSONObject jsonObject) {
        CustomFields customFields;
        if (jsonObject == null) {
            customFields = null;
        } else {
            customFields = new CustomFields();
            final JSONArray optJSONArray = jsonObject.optJSONArray("thumb_c");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                customFields.thumb_c = optJSONArray.optString(0);
                if (customFields.thumb_c.contains("custom")) {
                    customFields.thumb_m = customFields.thumb_c.replace("custom", "medium");
                }
            }
            final JSONArray optJSONArray2 = jsonObject.optJSONArray("views");
            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                customFields.views = optJSONArray2.optString(0);
            }
        }
        return customFields;
    }


    /**
     * 从本地缓存解析
     *
     * @param jsonObject
     * @return
     */
    public static CustomFields parseCache(final JSONObject jsonObject) {
        CustomFields customFields;
        if (jsonObject == null) {
            customFields = null;
        } else {
            customFields = new CustomFields();
            if ((jsonObject.optString("thumb_c") != null)) {
                customFields.thumb_c = jsonObject.optString("thumb_c");
                if (customFields.thumb_c.contains("custom")) {
                    customFields.thumb_m = customFields.thumb_c.replace("custom", "medium");
                }
            }
            customFields.views = jsonObject.optString("views");
        }
        return customFields;
    }


    public String getThumb_m() {
        return thumb_m;
    }

    public String getViews() {
        return views;
    }

    public Long getCustomFieldsId() {
        return CustomFieldsId;
    }

    public void setCustomFieldsId(Long customFieldsId) {
        CustomFieldsId = customFieldsId;
    }


    public void setViews(String views) {
        this.views = views;
    }


    public void setThumb_m(String thumb_m) {
        this.thumb_m = thumb_m;
    }


    public String getThumb_c() {
        return this.thumb_c;
    }


    public void setThumb_c(String thumb_c) {
        this.thumb_c = thumb_c;
    }
}
