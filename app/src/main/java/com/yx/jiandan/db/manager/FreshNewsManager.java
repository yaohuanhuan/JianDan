package com.yx.jiandan.db.manager;

import com.yx.jiandan.model.FreshNews;
import com.yx.jiandan.db.base.AbstractDatabaseManager;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by Y on 2016/9/1.
 */
public class FreshNewsManager extends AbstractDatabaseManager<FreshNews,Long> {
    @Override
    public AbstractDao<FreshNews, Long> getAbstractDao() {
        return daoSession.getFreshNewsDao();
    }
}
