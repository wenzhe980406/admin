package com.admin.service.system.impl;

import com.admin.dao.system.SystemDepartmentMapper;
import com.admin.dao.system.dto.QuerySystemDepartmentDTO;
import com.admin.dao.system.model.SystemDepartment;
import com.admin.dao.system.vo.SystemDepartmentListVO;
import com.admin.service.system.SystemDepartmentService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service实现
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Service
public class SystemDepartmentServiceImpl implements SystemDepartmentService {

    @Autowired
    private SystemDepartmentMapper systemDepartmentMapper;

    @Override
    public Integer create(SystemDepartment systemDepartment) {
        systemDepartmentMapper.insert(systemDepartment);
        return systemDepartment.getId();
    }

    @Override
    public void deleteById(Integer id) {
        SystemDepartment systemDepartment = new SystemDepartment();
        systemDepartment.setId(id);
        systemDepartment.setDeleted(Boolean.TRUE);
        this.updateById(systemDepartment);
    }

    @Override
    @Transactional
    public void deleteByIdInBatch(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) return;
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public void updateById(SystemDepartment systemDepartment) {
        systemDepartmentMapper.updateById(systemDepartment);
    }

    @Override
    @Transactional
    public void updateByIdInBatch(List<SystemDepartment> systemDepartments) {
        if (CollectionUtils.isEmpty(systemDepartments)) return;
        for (SystemDepartment systemDepartment: systemDepartments) {
            this.updateById(systemDepartment);
        }
    }

    @Override
    public SystemDepartment findById(Integer id) {
        return systemDepartmentMapper.selectById(id);
    }

    @Override
    public List<SystemDepartment> findByIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        QueryWrapper<SystemDepartment> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(SystemDepartment::getId, ids).eq(SystemDepartment::getDeleted, Boolean.FALSE);
        return systemDepartmentMapper.selectList(queryWrapper);
    }

    @Override
    public SystemDepartment findOne(SystemDepartment systemDepartment) {
        Wrapper<SystemDepartment> wrapper = new QueryWrapper<>(systemDepartment);
        return systemDepartmentMapper.selectOne(wrapper);
    }

    @Override
    public List<SystemDepartmentListVO> findList(QuerySystemDepartmentDTO dto) {
        return systemDepartmentMapper.selectManageList(dto);
    }

    @Override
    public long count(SystemDepartment systemDepartment) {
        Wrapper<SystemDepartment> wrapper = new QueryWrapper<>(systemDepartment);
        return systemDepartmentMapper.selectCount(wrapper);
    }

    @Override
    public List<Integer> findChildren(Integer departmentId) {
        List<Integer> pool = new ArrayList<>();
        this.fillChildren(pool, Arrays.asList(departmentId));
        return pool;
    }

    /**
     * 获取子部门ID
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    private void fillChildren(List<Integer> pool, List<Integer> parentIds) {
        QueryWrapper<SystemDepartment> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(SystemDepartment::getDeleted, Boolean.FALSE)
                .in(SystemDepartment::getParentId, parentIds);
        List<SystemDepartment> departments = systemDepartmentMapper.selectList(queryWrapper);
        List<Integer> ids = departments.stream().map(SystemDepartment::getId).collect(Collectors.toList());
        if (ids.size() > 0) {
            pool.addAll(ids);
            this.fillChildren(pool, ids);
        }
    }
}
