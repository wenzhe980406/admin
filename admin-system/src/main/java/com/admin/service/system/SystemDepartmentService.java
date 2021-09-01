package com.admin.service.system;

import com.admin.dao.system.dto.QuerySystemDepartmentDTO;
import com.admin.dao.system.model.SystemDepartment;
import com.admin.dao.system.vo.SystemDepartmentListVO;

import java.util.List;

/**
 * Service定义
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public interface SystemDepartmentService {

    /**
     * 创建
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    Integer create(SystemDepartment systemDepartment);

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
    void updateById(SystemDepartment systemDepartment);

    /**
     * 批量主键更新
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updateByIdInBatch(List<SystemDepartment> systemDepartments);

    /**
     * 主键查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    SystemDepartment findById(Integer id);

    /**
     * 根据ID集查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemDepartment> findByIds(List<Integer> ids);

    /**
     * 条件查询单条记录
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    SystemDepartment findOne(SystemDepartment systemDepartment);

    /**
     * 条件查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemDepartmentListVO> findList(QuerySystemDepartmentDTO dto);

    /**
     * 条件统计
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    long count(SystemDepartment systemDepartment);

    /**
     * 获取子孙部门ID集
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<Integer> findChildren(Integer departmentId);
}
