package com.admin.config.shiro;

import com.admin.core.exception.UnSafeSessionException;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 默认Token管理器
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Component
public class ShiroTokenManager {

    String build() {
        return UUID.randomUUID().toString();
    }

    void check(String token) throws UnSafeSessionException {
        if (token == null || token.length() != 36) {
            throw new UnSafeSessionException();
        }
    }
}
