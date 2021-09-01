package com.admin.dao.system.vo;

import com.admin.dao.system.model.SystemMenu;
import com.admin.dao.system.model.SystemPermission;
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
@ApiModel("系统角色列表视图对象")
public class SystemRoleListVO extends SystemRole {

    @ApiModelProperty(value = "角色拥有的权限列表")
    private List<SystemPermission> permissions;

    @ApiModelProperty(value = "角色拥有的菜单列表")
    private List<SystemMenu> menus;

    @ApiModelProperty(value = "创建人信息")
    private SystemUser createUserInfo;

    @ApiModelProperty(value = "更新人信息")
    private SystemUser updateUserInfo;
}
