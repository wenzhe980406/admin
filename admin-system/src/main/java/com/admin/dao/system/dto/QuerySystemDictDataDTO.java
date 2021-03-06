package com.admin.dao.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Data
@ApiModel("查询字典数据列表参数")
public class QuerySystemDictDataDTO implements Serializable {

    @ApiModelProperty(value = "字典ID")
    private Integer dictId;
}
