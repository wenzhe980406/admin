package com.admin.api.system;

import com.admin.api.BaseController;
import com.admin.core.annotation.trace.Trace;
import com.admin.core.model.ApiResponse;
import com.admin.core.utils.Monitor;
import com.admin.core.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Api(tags = "系统监听")
@Trace(exclude = true)
@RestController
@RequestMapping("/system/monitor")
public class SystemMonitorController extends BaseController {

    @ApiOperation("获取系统信息")
    @GetMapping("/getSystemInfo")
    @RequiresPermissions("system:monitor:query")
    public ApiResponse<Monitor> getSystemInfo () {
        return ApiResponse.success(Utils.Monitor.current());
    }
}
