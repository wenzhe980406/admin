package com.admin.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.admin.dao.system.dto.QuerySystemPermissionDTO;
import com.admin.dao.system.model.SystemPermission;
import com.admin.dao.system.vo.SystemPermissionListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemPermissionMapper extends BaseMapper<SystemPermission> {

    /**
     * 根据用户ID查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemPermission> selectByUserId(Integer userId);

    /**
     * 根据角色ID查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemPermission> selectByRoleId(Integer roleId);

    /**
     * 查询列表
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemPermissionListVO> selectManageList(@Param("dto") QuerySystemPermissionDTO dto, @Param("orderByClause") String orderByClause);

}
