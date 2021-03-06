package com.admin.service.system.impl;

import com.admin.dao.system.SystemPositionMapper;
import com.admin.dao.system.model.SystemPosition;
import com.admin.dao.system.vo.SystemPositionListVO;
import com.admin.service.system.SystemPositionService;
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
 * 岗位Service实现
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Service
public class SystemPositionServiceImpl implements SystemPositionService {

    @Autowired
    private SystemPositionMapper systemPositionMapper;

    @Override
    public Integer create(SystemPosition systemPosition) {
        systemPositionMapper.insert(systemPosition);
        return systemPosition.getId();
    }

    @Override
    public void deleteById(Integer id) {
        SystemPosition systemPosition = new SystemPosition();
        systemPosition.setId(id);
        systemPosition.setDeleted(Boolean.TRUE);
        this.updateById(systemPosition);
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
    public void updateById(SystemPosition systemPosition) {
        systemPositionMapper.updateById(systemPosition);
    }

    @Override
    @Transactional
    public void updateByIdInBatch(List<SystemPosition> systemPositions) {
        if (CollectionUtils.isEmpty(systemPositions)) return;
        for (SystemPosition systemPosition: systemPositions) {
            this.updateById(systemPosition);
        }
    }

    @Override
    public SystemPosition findById(Integer id) {
        return systemPositionMapper.selectById(id);
    }

    @Override
    public List<SystemPosition> findByIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        QueryWrapper<SystemPosition> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(SystemPosition::getId, ids).eq(SystemPosition::getDeleted, Boolean.FALSE);
        return systemPositionMapper.selectList(queryWrapper);
    }

    @Override
    public SystemPosition findOne(SystemPosition systemPosition) {
        return systemPositionMapper.selectOne(new QueryWrapper<>(systemPosition));
    }

    @Override
    public List<SystemPosition> findList(SystemPosition systemPosition) {
        return systemPositionMapper.selectList(new QueryWrapper<>(systemPosition));
    }

    @Override
    public List<SystemPositionListVO> findList() {
        return systemPositionMapper.selectManageList();
    }

    @Override
    public List<SystemPosition> findByUserId(Integer userId) {
        return systemPositionMapper.selectByUserId(userId);
    }

    @Override
    public long count(SystemPosition systemPosition) {
        return systemPositionMapper.selectCount(new QueryWrapper<>(systemPosition));
    }

    @Override
    public List<Integer> findChildren(Integer positionId) {
        List<Integer> pool = new ArrayList<>();
        this.fillChildren(pool, Arrays.asList(positionId));
        return pool;
    }

    /**
     * 获取子岗位ID
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    private void fillChildren(List<Integer> pool, List<Integer> parentIds) {
        QueryWrapper<SystemPosition> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(SystemPosition::getDeleted, Boolean.FALSE)
                .in(SystemPosition::getParentId, parentIds);
        List<SystemPosition> positions = systemPositionMapper.selectList(queryWrapper);
        List<Integer> ids = positions.stream().map(SystemPosition::getId).collect(Collectors.toList());
        if (ids.size() > 0) {
            pool.addAll(ids);
            this.fillChildren(pool, ids);
        }
    }
}
