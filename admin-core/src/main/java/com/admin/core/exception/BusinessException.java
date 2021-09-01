package com.admin.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 业务异常
 * @author 86
 * @date 2021/08/31 21:09
 */
@Data
@AllArgsConstructor
public class BusinessException extends Exception {

    private String code;

    private String message;
}
