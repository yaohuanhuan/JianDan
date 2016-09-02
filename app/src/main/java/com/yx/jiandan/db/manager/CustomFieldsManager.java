package com.yx.jiandan.db.manager;

import com.yx.jiandan.bean.CustomFields;
import com.yx.jiandan.db.base.AbstractDatabaseManager;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by Y on 2016/9/2.
 */
public class CustomFieldsManager extends AbstractDatabaseManager<CustomFields,Long> {
    @Override
    public AbstractDao<CustomFields, Long> getAbstractDao() {
        return daoSession.getCustomFieldsDao();
    }
}
