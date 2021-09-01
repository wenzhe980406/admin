package com.admin.biz.system.impl;

import com.admin.biz.system.SystemDepartmentBiz;
import com.admin.core.exception.BusinessException;
import com.admin.core.constants.ResponseStatus;
import com.admin.dao.system.model.SystemDepartment;
import com.admin.dao.system.vo.SystemDepartmentListVO;
import com.admin.service.aware.DepartmentDataPermissionAware;
import com.admin.service.system.SystemDepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemDepartmentBizImpl implements SystemDepartmentBiz {

    @Autowired
    private SystemDepartmentService systemDepartmentService;

    @Autowired
    private DepartmentDataPermissionAware departmentDataPermissionAware;

    @Override
    public Integer create(SystemDepartment department) {
        // 验证部门编码
        SystemDepartment queryDto = new SystemDepartment();
        queryDto.setCode(department.getCode());
        queryDto.setDeleted(Boolean.FALSE);
        SystemDepartment systemDepartment = systemDepartmentService.findOne(queryDto);
        if (systemDepartment != null) {
            throw new BusinessException(ResponseStatus.DATA_EXISTS.getCode(), "部门编码已存在");
        }
        return systemDepartmentService.create(department);
    }

    @Override
    public void updateById(SystemDepartment department) {
        // 验证部门编码
        SystemDepartment queryDto = new SystemDepartment();
        queryDto.setCode(department.getCode());
        queryDto.setDeleted(Boolean.FALSE);
        SystemDepartment systemDepartment = systemDepartmentService.findOne(queryDto);
        if (systemDepartment != null && !systemDepartment.getId().equals(department.getId())) {
            throw new BusinessException(ResponseStatus.DATA_EXISTS.getCode(), "部门编码已存在");
        }
        systemDepartmentService.updateById(department);
    }

    @Override
    public List<SystemDepartmentListVO> findTree() {
        return departmentDataPermissionAware.execute();
    }

    @Override
    public void deleteById(Integer id) {
        List<Integer> ids = systemDepartmentService.findChildren(id);
        ids.add(id);
        systemDepartmentService.deleteByIdInBatch(ids);
    }

    @Override
    @Transactional
    public void deleteByIdInBatch(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }
}
