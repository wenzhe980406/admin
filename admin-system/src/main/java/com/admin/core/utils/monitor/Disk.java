package com.admin.core.utils.monitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 磁盘信息
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Data
@ApiModel("磁盘信息")
public class Disk implements Serializable {

    @ApiModelProperty(value = "磁盘名称")
    private String name;

    @ApiModelProperty(value = "盘符路径")
    private String dir;

    @ApiModelProperty(value = "文件系统类型")
    private String fsType;

    @ApiModelProperty(value = "总大小")
    private double size;

    @ApiModelProperty(value = "可用大小")
    private double freeSpace;

    @ApiModelProperty(value = "获取已使用大小")
    public double getUsedSpace () {
        return size - freeSpace;
    }

    @ApiModelProperty(value = "使用率")
    public double getUseRatio () {
        return getUsedSpace() / size * 100;
    }
}
