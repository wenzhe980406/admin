package com.admin.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author changyw
 * @date 2021/9/2
 */
@Data
@ApiModel("系统用户")
public class User {

    @ApiModelProperty(value = "主键", example = "1")
    @NotNull(message = "主键不能为空")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    private String realname;

    @ApiModelProperty(value = "工号")
    private String empNo;

    @ApiModelProperty(value = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @ApiModelProperty(value = "性别")
    @NotBlank(message = "性别不能为空")
    private String sex;

    @ApiModelProperty(value = "邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty(value = "手机号码")
    @Pattern(message = "手机号码格式不正确", regexp = "^\\d*$")
    private String mobile;

    @ApiModelProperty(value = "头像")
    @NotBlank(message = "头像不能为空")
    private String avatar;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "初始密码不能为空")
    private String password;

    @ApiModelProperty(value = "盐")
    private String salt;

    @ApiModelProperty(value = "是否为固定用户", hidden = true)
    private Boolean fixed;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建人", example = "1", hidden = true)
    private Integer createUser;

    @ApiModelProperty(value = "更新人", example = "1", hidden = true)
    private Integer updateUser;

    @ApiModelProperty(value = "是否已删除", hidden = true)
    private Boolean deleted;
}
