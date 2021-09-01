package com.admin.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.admin.dao.system.model.SystemDataPermission;
import com.admin.dao.system.vo.SystemDataPermissionListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public interface SystemDataPermissionMapper extends BaseMapper<SystemDataPermission> {

    /**
     * 查询数据权限管理列表
     * @param dto 查询条件
     * @param orderByClause 排序SQL
     *
     * @return List<SystemDataPermissionListVO>
     */
    List<SystemDataPermissionListVO> selectManageList(@Param("dto") SystemDataPermission dto, @Param("orderByClause") String orderByClause);
}
