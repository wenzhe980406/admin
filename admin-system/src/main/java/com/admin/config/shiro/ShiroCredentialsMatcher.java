package com.admin.config.shiro;

import com.admin.core.utils.Utils;
import com.admin.dao.system.model.SystemUser;
import com.admin.service.system.SystemUserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Shiro密码比对处理
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Component
public class ShiroCredentialsMatcher extends HashedCredentialsMatcher {

    @Lazy
    @Autowired
    private SystemUserService systemUserService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        SystemUser queryUserDto = new SystemUser();
        queryUserDto.setUsername(usernamePasswordToken.getUsername());
        queryUserDto.setDeleted(Boolean.FALSE);
        SystemUser systemUser = systemUserService.findOne(queryUserDto);
        if (systemUser == null) {
            return Boolean.FALSE;
        }
        // 加密密码
        String pwd = Utils.Secure.encryptPassword(new String(usernamePasswordToken.getPassword()), systemUser.getSalt());
        // 比较密码
        return this.equals(pwd, systemUser.getPassword());
    }
}
