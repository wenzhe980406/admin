package com.admin.dao.system.dto;

import com.admin.dao.system.model.SystemUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Data
@ApiModel("创建用户参数")
public class CreateSystemUserDTO extends SystemUser {

    @ApiModelProperty(value = "岗位ID", example = "1")
    private List<Integer> positionIds;

    @ApiModelProperty(value = "部门ID", example = "1")
    private Integer departmentId;
}
