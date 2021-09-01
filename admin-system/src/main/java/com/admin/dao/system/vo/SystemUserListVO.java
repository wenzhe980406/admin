package com.admin.dao.system.vo;

import com.admin.dao.system.model.SystemDepartment;
import com.admin.dao.system.model.SystemPosition;
import com.admin.dao.system.model.SystemRole;
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
@ApiModel("系统用户列表视图对象")
public class SystemUserListVO extends SystemUser {

    @ApiModelProperty(value = "角色")
    private List<SystemRole> roles;

    @ApiModelProperty(value = "岗位信息")
    private List<SystemPosition> positions;

    @ApiModelProperty(value = "部门信息")
    private SystemDepartment department;

    @ApiModelProperty(value = "创建人信息")
    private SystemUser createUserInfo;

    @ApiModelProperty(value = "更新人信息")
    private SystemUser updateUserInfo;

}
