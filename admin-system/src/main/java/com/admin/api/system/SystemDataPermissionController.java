package com.admin.api.system;

import com.admin.api.BaseController;
import com.admin.biz.system.SystemDataPermissionBiz;
import com.admin.core.annotation.pr.PreventRepeat;
import com.admin.core.annotation.trace.Trace;
import com.admin.core.constants.DataPermissionConstants;
import com.admin.core.constants.OperaType;
import com.admin.core.model.ApiResponse;
import com.admin.core.model.PageData;
import com.admin.core.model.PageWrap;
import com.admin.dao.system.model.SystemDataPermission;
import com.admin.dao.system.vo.SystemDataPermissionListVO;
import com.admin.service.system.SystemDataPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;    
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@RestController
@RequestMapping("/system/dataPermission")
@Api(tags = "系统数据权限")
public class SystemDataPermissionController extends BaseController {

    @Autowired
    private SystemDataPermissionService systemDataPermissionService;

    @Autowired
    private SystemDataPermissionBiz systemDataPermissionBiz;

    @PreventRepeat
    @ApiOperation("新建")
    @PostMapping("/create")
    @RequiresPermissions("system:datapermission:create")
    public ApiResponse create(@Validated(OperaType.Create.class) @RequestBody SystemDataPermission systemDataPermission) {
        return ApiResponse.success(systemDataPermissionBiz.create(systemDataPermission));
    }

    @ApiOperation("根据ID删除")
    @GetMapping("/delete/{id}")
    @RequiresPermissions("system:datapermission:delete")
    public ApiResponse deleteById(@PathVariable Integer id) {
        systemDataPermissionService.deleteById(id);
        return ApiResponse.success(null);
    }

    @ApiOperation("批量删除")
    @GetMapping("/delete/batch")
    @RequiresPermissions("system:datapermission:delete")
    public ApiResponse deleteByIdInBatch(@RequestParam String ids) {
        String [] idArray = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String id : idArray) {
            idList.add(Integer.valueOf(id));
        }
        systemDataPermissionService.deleteByIdInBatch(idList);
        return ApiResponse.success(null);
    }

    @ApiOperation("修改")
    @PostMapping("/updateById")
    @RequiresPermissions("system:datapermission:update")
    public ApiResponse updateById(@Validated(OperaType.Update.class) @RequestBody SystemDataPermission systemDataPermission) {
        systemDataPermissionBiz.update(systemDataPermission);
        return ApiResponse.success(null);
    }

    @ApiOperation("修改状态")
    @PostMapping("/updateStatus")
    @RequiresPermissions("system:datapermission:update")
    public ApiResponse updateStatus(@Validated(OperaType.UpdateStatus.class) @RequestBody SystemDataPermission systemDataPermission) {
        systemDataPermissionBiz.updateStatus(systemDataPermission);
        return ApiResponse.success(null);
    }

    @ApiOperation("分页查询")
    @PostMapping("/page")
    @RequiresPermissions("system:datapermission:query")
    public ApiResponse<PageData<SystemDataPermissionListVO>> findPage (@RequestBody PageWrap<SystemDataPermission> pageWrap) {
        return ApiResponse.success(systemDataPermissionService.findPage(pageWrap));
    }

    @Trace(exclude = true)
    @ApiOperation("查询数据权限类型")
    @GetMapping("/types")
    public ApiResponse<List<Map<String, Object>>> findTypes () {
        return ApiResponse.success(DataPermissionConstants.Type.valueList());
    }

    @Trace(exclude = true)
    @ApiOperation("查询数据权限模块")
    @GetMapping("/modules")
    public ApiResponse<List<Map<String, Object>>> findModules () {
        return ApiResponse.success(DataPermissionConstants.Module.valueList());
    }
}
