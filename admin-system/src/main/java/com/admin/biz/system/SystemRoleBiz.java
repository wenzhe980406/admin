package com.admin.biz.system;

import com.admin.dao.system.dto.CreateRoleMenuDTO;
import com.admin.dao.system.dto.CreateRolePermissionDTO;
import com.admin.dao.system.model.SystemRole;

import java.util.List;

/**
 * 角色权限业务处理
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public interface SystemRoleBiz {

    /**
     * 新建
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    Integer create (SystemRole systemRole);

    /**
     * 修改
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updateById(SystemRole systemRole);

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
     * 创建角色权限
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void createRolePermission(CreateRolePermissionDTO dto);

    /**
     * 创建角色菜单
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void createRoleMenu(CreateRoleMenuDTO dto);
}
