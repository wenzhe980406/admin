package com.admin.api.system;

import com.admin.api.BaseController;
import com.admin.core.annotation.excel.ExcelExporter;
import com.admin.core.model.ApiResponse;
import com.admin.core.model.PageData;
import com.admin.core.model.PageWrap;
import com.admin.dao.system.dto.QuerySystemLoginLogDTO;
import com.admin.dao.system.model.SystemLoginLog;
import com.admin.service.system.SystemLoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;    
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@RestController
@RequestMapping("/system/loginLog")
@Api(tags = "登录日志")
public class SystemLoginLogController extends BaseController {

    @Autowired
    private SystemLoginLogService systemLoginLogService;

    @PostMapping("/page")
    @ApiOperation("分页查询")
    @RequiresPermissions("system:loginLog:query")
    public ApiResponse<PageData<SystemLoginLog>> findPage (@RequestBody PageWrap<QuerySystemLoginLogDTO> pageWrap) {
        return ApiResponse.success(systemLoginLogService.findPage(pageWrap));
    }

    @PostMapping("/exportExcel")
    @ApiOperation("导出Excel")
    @RequiresPermissions("system:loginLog:query")
    public void export (@RequestBody PageWrap<QuerySystemLoginLogDTO> pageWrap, HttpServletResponse response) {
        ExcelExporter.build(SystemLoginLog.class).export(systemLoginLogService.findPage(pageWrap).getRecords(), "登录日志", response);
    }
}
