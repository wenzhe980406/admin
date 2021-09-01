package com.admin.biz.system;

import com.admin.dao.system.model.SystemDictData;

/**
 * 字典数据业务处理
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public interface SystemDictDataBiz {

    /**
     * 新建
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    Integer create(SystemDictData systemDictData);

    /**
     * 编辑
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updateById(SystemDictData systemDictData);
}
