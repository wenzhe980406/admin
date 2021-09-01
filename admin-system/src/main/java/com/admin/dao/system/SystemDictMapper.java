package com.admin.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.admin.dao.system.dto.QuerySystemDictDTO;
import com.admin.dao.system.model.SystemDict;
import com.admin.dao.system.vo.SystemDictListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemDictMapper extends BaseMapper<SystemDict> {

    /**
     * 查询字典管理列表
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemDictListVO> selectManageList(@Param("dto") QuerySystemDictDTO dto, @Param("orderByClause") String orderByClause);
}
