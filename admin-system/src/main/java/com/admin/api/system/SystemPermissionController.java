package com.admin.api.system;

import com.admin.api.BaseController;
import com.admin.biz.system.SystemPermissionBiz;
import com.admin.core.annotation.pr.PreventRepeat;
import com.admin.core.model.ApiResponse;
import com.admin.core.constants.OperaType;
import com.admin.core.model.PageData;
import com.admin.core.model.PageWrap;
import com.admin.dao.system.dto.QuerySystemPermissionDTO;
import com.admin.dao.system.model.SystemPermission;
import com.admin.dao.system.vo.SystemPermissionListVO;
import com.admin.service.system.SystemPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Api(tags = "系统权限")
@RestController
@RequestMapping("/system/permission")
public class SystemPermissionController extends BaseController {

    @Autowired
    private SystemPermissionService systemPermissionService;

    @Autowired
    private SystemPermissionBiz systemPermissionBiz;

    @PreventRepeat
    @ApiOperation("新建")
    @PostMapping("/create")
    @RequiresPermissions("system:permission:create")
    public ApiResponse create(@Validated(OperaType.Create.class) @RequestBody SystemPermission systemPermission) {
        return ApiResponse.success(systemPermissionBiz.create(systemPermission));
    }

    @ApiOperation("删除")
    @GetMapping("/delete/{id}")
    @RequiresPermissions("system:permission:delete")
    public ApiResponse deleteById(@PathVariable Integer id) {
        systemPermissionBiz.deleteById(id);
        return ApiResponse.success(null);
    }

    @ApiOperation("批量删除")
    @GetMapping("/delete/batch")
    @RequiresPermissions("system:permission:delete")
    public ApiResponse deleteByIdInBatch(@RequestParam String ids) {
        String [] idArray = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String id : idArray) {
            idList.add(Integer.valueOf(id));
        }
        systemPermissionBiz.deleteByIdInBatch(idList);
        return ApiResponse.success(null);
    }

    @ApiOperation("修改")
    @PostMapping("/updateById")
    @RequiresPermissions("system:permission:update")
    public ApiResponse updateById(@Validated(OperaType.Update.class) @RequestBody SystemPermission systemPermission) {
        systemPermissionBiz.updateById(systemPermission);
        return ApiResponse.success(null);
    }

    @ApiOperation("分页查询")
    @PostMapping("/page")
    @RequiresPermissions("system:permission:query")
    public ApiResponse<PageData<SystemPermissionListVO>> findPage (@RequestBody PageWrap<QuerySystemPermissionDTO> pageWrap) {
        return ApiResponse.success(systemPermissionService.findPage(pageWrap));
    }

    @ApiOperation("查询所有")
    @GetMapping("/all")
    @RequiresPermissions("system:permission:query")
    public ApiResponse<List<SystemPermission>> findAll () {
        SystemPermission systemPermission = new SystemPermission();
        systemPermission.setDeleted(Boolean.FALSE);
        return ApiResponse.success(systemPermissionService.findList(systemPermission));
    }
}
