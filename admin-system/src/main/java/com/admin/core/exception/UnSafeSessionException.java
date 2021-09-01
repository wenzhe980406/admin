package com.admin.core.exception;

/**
 * 不安全的会话异常
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public class UnSafeSessionException extends RuntimeException {

    public UnSafeSessionException () {
        super("不安全的会话");
    }
}
