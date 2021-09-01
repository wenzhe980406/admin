package com.admin.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.admin.dao.system.dto.QuerySystemDepartmentDTO;
import com.admin.dao.system.model.SystemDepartment;
import com.admin.dao.system.vo.SystemDepartmentListVO;

import java.util.List;

public interface SystemDepartmentMapper extends BaseMapper<SystemDepartment> {

    /**
     * 查询部门
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemDepartmentListVO> selectManageList(QuerySystemDepartmentDTO dto);
}
