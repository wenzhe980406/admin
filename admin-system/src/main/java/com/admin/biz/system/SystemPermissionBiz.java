package com.admin.biz.system;

import com.admin.dao.system.model.SystemPermission;

import java.util.List;

/**
 * 系统权限业务处理
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public interface SystemPermissionBiz {

    /**
     * 删除
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void deleteById(Integer id);

    /**
     * 批量删除
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void deleteByIdInBatch(List<Integer> ids);

    /**
     * 创建
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    Integer create(SystemPermission systemPermission);

    /**
     * 修改
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updateById(SystemPermission systemPermission);
}
