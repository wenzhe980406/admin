package com.admin.dao.system.dto;

import com.admin.dao.system.model.SystemTraceLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 跟踪日志查询
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Data
@ApiModel("查询跟踪日志参数")
public class QuerySystemTraceLogDTO extends SystemTraceLog {

    @ApiModelProperty("操作开始时间")
    private Date startTime;

    @ApiModelProperty("操作结束时间")
    private Date endTime;
}
