package com.yx.jiandan.db.manager;

import com.yx.jiandan.bean.Author;
import com.yx.jiandan.db.base.AbstractDatabaseManager;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by Y on 2016/9/2.
 */
public class AuthorManageer extends AbstractDatabaseManager<Author,Long> {
    @Override
    public AbstractDao<Author, Long> getAbstractDao() {
        return daoSession.getAuthorDao();
    }
}
