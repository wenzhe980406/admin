package com.admin.dao.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Data
@ApiModel("菜单节点视图对象")
public class SystemMenuNodeVO implements Serializable {

    @ApiModelProperty(value = "菜单ID")
    private Integer id;

    @ApiModelProperty(value = "菜单名称")
    private String label;

    @ApiModelProperty(value = "菜单唯一标识（前端专用）")
    private String index;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "菜单地址")
    private String url;

    @ApiModelProperty(value = "子菜单")
    private List<SystemMenuNodeVO> children;
}
