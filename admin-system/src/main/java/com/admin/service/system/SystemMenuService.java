package com.admin.service.system;

import com.admin.dao.system.model.SystemMenu;
import com.admin.dao.system.vo.SystemMenuListVO;

import java.util.List;

/**
 * 系统菜单Service定义
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public interface SystemMenuService {

    /**
     * 创建
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    Integer create(SystemMenu systemMenu);

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
    void updateById(SystemMenu systemMenu);

    /**
     * 批量主键更新
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updateByIdInBatch(List<SystemMenu> systemMenus);

    /**
     * 主键查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    SystemMenu findById(Integer id);

    /**
     * 条件查询单条记录
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    SystemMenu findOne(SystemMenu systemMenu);

    /**
     * 条件查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemMenu> findList(SystemMenu systemMenu);

    /**
     * 查询列表
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemMenuListVO> findList();

    /**
     * 查询一级菜单列表
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemMenu> findRootList();

    /**
     * 查询用户ID查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemMenu> findByUserId(Integer userId);

    /**
     * 根据角色ID查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemMenu> findByRoleId(Integer roleId);

    /**
     * 条件统计
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    long count(SystemMenu systemMenu);

    /**
     * 查询子菜单
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<Integer> findChildren(Integer menuId);
}
