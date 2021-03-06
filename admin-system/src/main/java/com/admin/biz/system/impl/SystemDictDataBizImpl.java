package com.admin.biz.system.impl;

import com.admin.biz.system.SystemDictDataBiz;
import com.admin.core.exception.BusinessException;
import com.admin.core.constants.ResponseStatus;
import com.admin.dao.system.model.SystemDictData;
import com.admin.service.system.SystemDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemDictDataBizImpl implements SystemDictDataBiz {

    @Autowired
    private SystemDictDataService systemDictDataService;

    @Override
    public Integer create(SystemDictData systemDictData) {
        SystemDictData queryDto = new SystemDictData();
        queryDto.setDictId(systemDictData.getDictId());
        queryDto.setCode(systemDictData.getCode());
        queryDto.setDeleted(Boolean.FALSE);
        SystemDictData dictData = systemDictDataService.findOne(queryDto);
        if (dictData != null) {
            throw new BusinessException(ResponseStatus.DATA_EXISTS.getCode(), "数据值已存在");
        }
        return systemDictDataService.create(systemDictData);
    }

    @Override
    public void updateById(SystemDictData systemDictData) {
        SystemDictData queryDto = new SystemDictData();
        queryDto.setDictId(systemDictData.getDictId());
        queryDto.setCode(systemDictData.getCode());
        queryDto.setDeleted(Boolean.FALSE);
        SystemDictData dictData = systemDictDataService.findOne(queryDto);
        if (dictData != null && !dictData.getId().equals(systemDictData.getId())) {
            throw new BusinessException(ResponseStatus.DATA_EXISTS.getCode(), "数据值已存在");
        }
        systemDictDataService.updateById(systemDictData);
    }
}
