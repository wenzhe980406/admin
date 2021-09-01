package com.admin.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统常量
 * @author 86
 * @date 2021/08/31 21:09
 */
public interface Constants {

    /**
     * 通用的响应状态定义
     * @author 86
     * @date 2021/08/31 21:09
     */
    @Getter
    @AllArgsConstructor
    enum ResponseStatus implements com.admin.core.model.ResponseStatus {
        SUCCESS("SUCCESS", "请求成功"),
        FAILED("FAILED", "请求失败"),
        ;
        private String code;

        private String message;
    }

}
