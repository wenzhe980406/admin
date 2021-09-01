package com.admin.service.system;

import com.admin.core.model.PageData;
import com.admin.core.model.PageWrap;
import com.admin.dao.system.dto.QuerySystemUserDTO;
import com.admin.dao.system.model.SystemUser;
import com.admin.dao.system.vo.SystemUserListVO;

import java.util.List;

/**
 * 系统用户Service定义
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public interface SystemUserService {

    /**
     * 创建
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    Integer create(SystemUser systemUser);

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
    void updateById(SystemUser systemUser);

    /**
     * 批量主键更新
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updateByIdInBatch(List<SystemUser> systemUsers);

    /**
     * 主键查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    SystemUser findById(Integer id);

    /**
     * 条件查询单条记录
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    SystemUser findOne(SystemUser systemUser);

    /**
     * 条件查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemUser> findList(SystemUser systemUser);
  
    /**
     * 分页查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    PageData<SystemUserListVO> findPage(PageWrap<QuerySystemUserDTO> pageWrap);

    /**
     * 条件统计
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    long count(SystemUser systemUser);
}
