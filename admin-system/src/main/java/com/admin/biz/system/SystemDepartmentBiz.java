package com.admin.biz.system;

import com.admin.dao.system.model.SystemDepartment;
import com.admin.dao.system.vo.SystemDepartmentListVO;

import java.util.List;

/**
 * 部门管理业务处理
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public interface SystemDepartmentBiz {

    /**
     * 新建
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    Integer create(SystemDepartment department);

    /**
     * 编辑
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updateById(SystemDepartment department);

    /**
     * 查询部门列表树
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemDepartmentListVO> findTree();

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
}
