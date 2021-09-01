package com.admin.biz.system;

import com.admin.dao.system.model.SystemDict;

/**
 * 字典业务处理
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public interface SystemDictBiz {

    /**
     * 创建
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    Integer create(SystemDict systemDict);

    /**
     * 编辑
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updateById(SystemDict systemDict);

}
