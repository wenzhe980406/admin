package com.admin.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.admin.dao.system.dto.QuerySystemUserDTO;
import com.admin.dao.system.model.SystemUser;
import com.admin.dao.system.vo.SystemUserListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemUserMapper extends BaseMapper<SystemUser> {

    /**
     * 查询用户列表
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemUserListVO> selectManageList(@Param("dto") QuerySystemUserDTO dto, @Param("orderByClause") String orderByClause);

}
