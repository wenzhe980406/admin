package com.admin.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.admin.dao.system.model.SystemPosition;
import com.admin.dao.system.vo.SystemPositionListVO;

import java.util.List;

public interface SystemPositionMapper extends BaseMapper<SystemPosition> {

    /**
     * 查询管理列表
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemPositionListVO> selectManageList();

    /**
     * 查询用户岗位列表
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemPosition> selectByUserId(Integer userId);
}
