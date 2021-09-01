package com.admin.service.system;

import com.admin.core.model.PageData;
import com.admin.core.model.PageWrap;
import com.admin.dao.system.dto.QuerySystemLoginLogDTO;
import com.admin.dao.system.model.SystemLoginLog;
import java.util.List;

/**
 * 登录日志Service定义
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public interface SystemLoginLogService {

    /**
     * 创建
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    Integer create(SystemLoginLog systemLoginLog);

    /**
     * 主键删除
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void deleteById(Integer id);

    /**
     * 删除
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void delete(SystemLoginLog systemLoginLog);

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
    void updateById(SystemLoginLog systemLoginLog);

    /**
     * 批量主键更新
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updateByIdInBatch(List<SystemLoginLog> systemLoginLogs);

    /**
     * 主键查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    SystemLoginLog findById(Integer id);

    /**
     * 条件查询单条记录
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    SystemLoginLog findOne(SystemLoginLog systemLoginLog);

    /**
     * 条件查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    List<SystemLoginLog> findList(SystemLoginLog systemLoginLog);
  
    /**
     * 分页查询
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    PageData<SystemLoginLog> findPage(PageWrap<QuerySystemLoginLogDTO> pageWrap);

    /**
     * 条件统计
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    long count(SystemLoginLog systemLoginLog);
}
