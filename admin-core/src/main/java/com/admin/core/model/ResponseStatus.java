package com.admin.core.model;

/**
 * 响应状态
 * @author 86
 * @date 2021/08/31 21:09
 */
public interface ResponseStatus {

    /**
     * 获取响应码
     * @author 86
     * @date 2021/08/31 21:09
     */
    String getCode();

    /**
     * 获取响应消息
     * @author 86
     * @date 2021/08/31 21:09
     */
    String getMessage();
}
