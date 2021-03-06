package com.admin.dao.system.dto;

import com.admin.dao.system.model.SystemLoginLog;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 查询登录日志参数
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Data
public class QuerySystemLoginLogDTO extends SystemLoginLog {

    @ApiModelProperty("登录开始时间")
    private Date startTime;

    @ApiModelProperty("登录结束时间")
    private Date endTime;
}
