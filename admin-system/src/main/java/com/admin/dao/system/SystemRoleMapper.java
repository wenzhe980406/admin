package com.admin.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.admin.dao.system.dto.QuerySystemRoleDTO;
import com.admin.dao.system.model.SystemRole;
import com.admin.dao.system.vo.SystemRoleListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemRoleMapper extends BaseMapper<SystemRole> {

    /**
     * 查询角色列表
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemRoleListVO> selectManageList(@Param("dto") QuerySystemRoleDTO dto, @Param("orderByClause") String orderByClause);

    /**
     * 根据用户ID查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemRole> selectByUserId(Integer userId);

}
