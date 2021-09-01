package com.admin.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.admin.dao.system.model.SystemMenu;
import com.admin.dao.system.vo.SystemMenuListVO;

import java.util.List;

public interface SystemMenuMapper extends BaseMapper<SystemMenu> {

    /**
     * 查询列表
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemMenuListVO> selectManageList();

    /**
     * 根据用户ID查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemMenu> selectByUserId(Integer userId);

    /**
     * 根据角色ID查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemMenu> selectByRoleId(Integer roleId);

}
