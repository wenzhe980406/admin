package com.admin.core.aware;

import com.admin.core.constants.DataPermissionConstants;

import java.lang.annotation.*;

/**
 * 数据权限控制注解
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataPermissionMapping {

    /**
     * 权限类型
     */
    DataPermissionConstants.Type value();

    /**
     * 优先级
     */
    int priority();

    /**
     * 注入用户ID参数
     */
    boolean injectUser() default false;

    /**
     * 注入自定义数据参数
     */
    boolean injectCustomData () default false;
}
