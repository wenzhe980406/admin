package com.admin.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.admin.core.model.PageData;
import com.admin.core.model.PageWrap;
import com.admin.dao.system.SystemDictMapper;
import com.admin.dao.system.dto.QuerySystemDictDTO;
import com.admin.dao.system.model.SystemDict;
import com.admin.dao.system.vo.SystemDictListVO;
import com.admin.service.system.SystemDictService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 字典Service实现
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Service
public class SystemDictServiceImpl implements SystemDictService {

    @Autowired
    private SystemDictMapper systemDictMapper;

    @Override
    public Integer create(SystemDict systemDict) {
        systemDictMapper.insert(systemDict);
        return systemDict.getId();
    }

    @Override
    public void deleteById(Integer id) {
        SystemDict systemDict = new SystemDict();
        systemDict.setId(id);
        systemDict.setDeleted(Boolean.TRUE);
        this.updateById(systemDict);
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
    public void updateById(SystemDict systemDict) {
        systemDictMapper.updateById(systemDict);
    }

    @Override
    @Transactional
    public void updateByIdInBatch(List<SystemDict> systemDicts) {
        if (CollectionUtils.isEmpty(systemDicts)) return;
        for (SystemDict systemDict: systemDicts) {
            this.updateById(systemDict);
        }
    }

    @Override
    public SystemDict findById(Integer id) {
        return systemDictMapper.selectById(id);
    }

    @Override
    public SystemDict findOne(SystemDict systemDict) {
        Wrapper<SystemDict> wrapper = new QueryWrapper<>(systemDict);
        return systemDictMapper.selectOne(wrapper);
    }

    @Override
    public List<SystemDict> findList(SystemDict systemDict) {
        Wrapper<SystemDict> wrapper = new QueryWrapper<>(systemDict);
        return systemDictMapper.selectList(wrapper);
    }
  
    @Override
    public PageData<SystemDictListVO> findPage(PageWrap<QuerySystemDictDTO> pageWrap) {
        PageHelper.startPage(pageWrap.getPage(), pageWrap.getCapacity());
        return PageData.from(new PageInfo<>(systemDictMapper.selectManageList(pageWrap.getModel(), pageWrap.getOrderByClause())));
    }

    @Override
    public long count(SystemDict systemDict) {
        Wrapper<SystemDict> wrapper = new QueryWrapper<>(systemDict);
        return systemDictMapper.selectCount(wrapper);
    }
}
