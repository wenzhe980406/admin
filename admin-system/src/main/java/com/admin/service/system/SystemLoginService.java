package com.admin.service.system;

import com.admin.dao.system.dto.LoginDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统登录
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public interface SystemLoginService {

    /**
     * 密码登录
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    String loginByPassword (LoginDTO dto, HttpServletRequest request);
}
