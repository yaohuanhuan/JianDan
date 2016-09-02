package com.yx.jiandan.db.manager;

import com.yx.jiandan.bean.Tags;
import com.yx.jiandan.db.base.AbstractDatabaseManager;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by Y on 2016/9/2.
 */
public class TagsManager extends AbstractDatabaseManager<Tags,Long> {
    @Override
    public AbstractDao<Tags, Long> getAbstractDao() {
        return daoSession.getTagsDao();
    }
}
