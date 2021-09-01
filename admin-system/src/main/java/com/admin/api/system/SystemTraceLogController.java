package com.admin.api.system;

import com.admin.api.BaseController;
import com.admin.core.annotation.excel.ExcelExporter;
import com.admin.core.model.ApiResponse;
import com.admin.core.model.PageData;
import com.admin.core.model.PageWrap;
import com.admin.dao.system.dto.QuerySystemTraceLogDTO;
import com.admin.dao.system.model.SystemTraceLog;
import com.admin.service.system.SystemTraceLogService;
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
@Api(tags = "跟踪日志")
@RestController
@RequestMapping("/system/traceLog")
public class SystemTraceLogController extends BaseController {

    @Autowired
    private SystemTraceLogService systemTraceLogService;

    @ApiOperation("分页查询")
    @PostMapping("/page")
    @RequiresPermissions("system:traceLog:query")
    public ApiResponse findPage (@RequestBody PageWrap<QuerySystemTraceLogDTO> pageWrap) {
        return ApiResponse.success(systemTraceLogService.findPage(pageWrap));
    }

    @ApiOperation("导出Excel")
    @PostMapping("/exportExcel")
    @RequiresPermissions("system:traceLog:query")
    public void exportExcel (@RequestBody PageWrap<QuerySystemTraceLogDTO> pageWrap, HttpServletResponse response) {
        ExcelExporter.build(SystemTraceLog.class).export(systemTraceLogService.findPage(pageWrap).getRecords(), "操作日志", response);
    }
}
