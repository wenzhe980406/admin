package com.admin.service.system;

import com.admin.core.model.PageData;
import com.admin.core.model.PageWrap;
import com.admin.dao.system.dto.QuerySystemRoleDTO;
import com.admin.dao.system.model.SystemRole;
import com.admin.dao.system.vo.SystemRoleListVO;

import java.util.List;

/**
 * 系统角色Service定义
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public interface SystemRoleService {

    /**
     * 创建
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    Integer create(SystemRole systemRole);

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
    void updateById(SystemRole systemRole);

    /**
     * 批量主键更新
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updateByIdInBatch(List<SystemRole> systemRoles);

    /**
     * 主键查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    SystemRole findById(Integer id);

    /**
     * 根据用户ID查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemRole> findByUserId(Integer userId);

    /**
     * 条件查询单条记录
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    SystemRole findOne(SystemRole systemRole);

    /**
     * 条件查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemRole> findList(SystemRole systemRole);
  
    /**
     * 分页查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    PageData<SystemRoleListVO> findPage(PageWrap<QuerySystemRoleDTO> pageWrap);

    /**
     * 条件统计
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    long count(SystemRole systemRole);
}
