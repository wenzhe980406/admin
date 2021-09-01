package com.admin.dao.system.vo;

import com.admin.dao.system.model.SystemPermission;
import com.admin.dao.system.model.SystemUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Data
@ApiModel("系统权限列表视图对象")
public class SystemPermissionListVO extends SystemPermission {

    @ApiModelProperty(value = "创建人信息")
    private SystemUser createUserInfo;

    @ApiModelProperty(value = "更新人信息")
    private SystemUser updateUserInfo;
}
