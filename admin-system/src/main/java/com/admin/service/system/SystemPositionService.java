package com.admin.service.system;

import com.admin.dao.system.model.SystemPosition;
import com.admin.dao.system.vo.SystemPositionListVO;

import java.util.List;

/**
 * 岗位Service定义
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public interface SystemPositionService {

    /**
     * 创建
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    Integer create(SystemPosition systemPosition);

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
    void updateById(SystemPosition systemPosition);

    /**
     * 批量主键更新
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updateByIdInBatch(List<SystemPosition> systemPositions);

    /**
     * 主键查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    SystemPosition findById(Integer id);

    /**
     * 根据ID集查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemPosition> findByIds(List<Integer> ids);

    /**
     * 条件查询单条记录
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    SystemPosition findOne(SystemPosition systemPosition);

    /**
     * 条件查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemPosition> findList(SystemPosition systemPosition);

    /**
     * 查询管理列表
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemPositionListVO> findList();

    /**
     * 查询用户岗位列表
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemPosition> findByUserId(Integer userId);

    /**
     * 条件统计
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    long count(SystemPosition systemPosition);

    /**
     * 查询子岗位ID集
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<Integer> findChildren(Integer positionId);
}
