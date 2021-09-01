package com.admin.service.system;

import com.admin.core.model.PageData;
import com.admin.core.model.PageWrap;
import com.admin.dao.system.dto.QuerySystemPermissionDTO;
import com.admin.dao.system.model.SystemPermission;
import com.admin.dao.system.vo.SystemPermissionListVO;

import java.util.List;

/**
 * 系统权限Service定义
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public interface SystemPermissionService {

    /**
     * 创建
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    Integer create(SystemPermission systemPermission);

    /**
     * 主键删除
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void deleteById(Integer id);

    /**
     * 批量主键删除
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void deleteByIdInBatch(List<Integer> ids);

    /**
     * 主键更新
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updateById(SystemPermission systemPermission);

    /**
     * 批量主键更新
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updateByIdInBatch(List<SystemPermission> systemPermissions);

    /**
     * 主键查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    SystemPermission findById(Integer id);

    /**
     * 根据用户ID查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemPermission> findByUserId(Integer userId);

    /**
     * 根据角色ID查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemPermission> findByRoleId(Integer roleId);

    /**
     * 条件查询单条记录
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    SystemPermission findOne(SystemPermission systemPermission);

    /**
     * 条件查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemPermission> findList(SystemPermission systemPermission);
  
    /**
     * 分页查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    PageData<SystemPermissionListVO> findPage(PageWrap<QuerySystemPermissionDTO> pageWrap);

    /**
     * 条件统计
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    long count(SystemPermission systemPermission);
}
