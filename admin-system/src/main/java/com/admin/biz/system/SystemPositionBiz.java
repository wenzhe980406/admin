package com.admin.biz.system;


import com.admin.dao.system.model.SystemPosition;
import com.admin.dao.system.vo.SystemPositionListVO;

import java.util.List;

public interface SystemPositionBiz {

    /**
     * 新建
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    Integer create(SystemPosition systemPosition);

    /**
     * 编辑
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updateById(SystemPosition systemPosition);

    /**
     * 查询岗位列表树
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemPositionListVO> findTree();

    /**
     * 删除
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void deleteById(Integer id);

    /**
     * 批量删除
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void deleteByIdInBatch(List<Integer> ids);
}
