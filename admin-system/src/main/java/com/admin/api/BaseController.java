package com.admin.api;

import com.admin.core.model.LoginUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;

/**
 * Controller基类
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Slf4j
public class BaseController {

    /**
     * 获取当前登录用户
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    protected LoginUserInfo getLoginUser () {
        return (LoginUserInfo)SecurityUtils.getSubject().getPrincipal();
    }

}
