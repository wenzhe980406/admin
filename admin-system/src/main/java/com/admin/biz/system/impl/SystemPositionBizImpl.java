package com.admin.biz.system.impl;

import com.admin.biz.system.SystemPositionBiz;
import com.admin.core.exception.BusinessException;
import com.admin.core.constants.ResponseStatus;
import com.admin.dao.system.model.SystemPosition;
import com.admin.dao.system.vo.SystemPositionListVO;
import com.admin.service.aware.PositionDataPermissionAware;
import com.admin.service.system.SystemPositionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemPositionBizImpl implements SystemPositionBiz {

    @Autowired
    private SystemPositionService systemPositionService;

    @Autowired
    private PositionDataPermissionAware positionDataPermissionAware;

    @Override
    public Integer create(SystemPosition systemPosition) {
        SystemPosition queryDto = new SystemPosition();
        queryDto.setCode(systemPosition.getCode());
        queryDto.setDeleted(Boolean.FALSE);
        SystemPosition position = systemPositionService.findOne(queryDto);
        if (position != null) {
            throw new BusinessException(ResponseStatus.DATA_EXISTS.getCode(), "岗位编码已存在");
        }
        return systemPositionService.create(systemPosition);
    }

    @Override
    public void updateById(SystemPosition systemPosition) {
        SystemPosition queryDto = new SystemPosition();
        queryDto.setCode(systemPosition.getCode());
        queryDto.setDeleted(Boolean.FALSE);
        SystemPosition position = systemPositionService.findOne(queryDto);
        if (position != null && !position.getId().equals(systemPosition.getId())) {
            throw new BusinessException(ResponseStatus.DATA_EXISTS.getCode(), "岗位编码已存在");
        }
        systemPositionService.updateById(systemPosition);
    }

    @Override
    public List<SystemPositionListVO> findTree() {
        return positionDataPermissionAware.execute();
    }

    @Override
    public void deleteById(Integer id) {
        List<Integer> ids = systemPositionService.findChildren(id);
        ids.add(id);
        systemPositionService.deleteByIdInBatch(ids);
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
