package com.admin.api.common;

import com.admin.api.BaseController;
import com.admin.core.annotation.trace.Trace;
import com.admin.core.model.ApiResponse;
import com.admin.service.common.CaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Api(tags = "图片验证码接口")
@Trace(exclude = true)
@RestController
@RequestMapping("/common")
public class CaptchaController extends BaseController {

    @Autowired
    private CaptchaService captchaService;

    /**
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    @ApiOperation("获取图片验证码")
    @GetMapping("/captcha")
    public ApiResponse<CaptchaService.Captcha> getCaptcha() {
        return ApiResponse.success(captchaService.genCaptcha());
    }
}
