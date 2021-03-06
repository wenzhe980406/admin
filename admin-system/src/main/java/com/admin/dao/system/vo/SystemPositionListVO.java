package com.admin.dao.system.vo;

import com.admin.dao.system.model.SystemPosition;
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
@ApiModel("岗位列表视图对象")
public class SystemPositionListVO extends SystemPosition {

    @ApiModelProperty(value = "岗位人数")
    private long userCount;

    @ApiModelProperty(value = "子岗位列表")
    private List<SystemPositionListVO> children;

    @ApiModelProperty(value = "是否包含子部门")
    private Boolean hasChildren;

    @ApiModelProperty(value = "创建人信息")
    private SystemUser createUserInfo;

    @ApiModelProperty(value = "更新人信息")
    private SystemUser updateUserInfo;
}
