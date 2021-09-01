package com.admin.api.system;

import com.admin.api.BaseController;
import com.admin.biz.system.SystemRoleBiz;
import com.admin.core.annotation.pr.PreventRepeat;
import com.admin.core.model.ApiResponse;
import com.admin.core.constants.OperaType;
import com.admin.core.model.PageData;
import com.admin.core.model.PageWrap;
import com.admin.dao.system.dto.CreateRoleMenuDTO;
import com.admin.dao.system.dto.CreateRolePermissionDTO;
import com.admin.dao.system.dto.QuerySystemRoleDTO;
import com.admin.dao.system.model.SystemRole;
import com.admin.dao.system.vo.SystemRoleListVO;
import com.admin.service.system.SystemRoleService;
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
@Api(tags = "系统角色")
@RestController
@RequestMapping("/system/role")
public class SystemRoleController extends BaseController {

    @Autowired
    private SystemRoleService systemRoleService;

    @Autowired
    private SystemRoleBiz systemRoleBiz;

    @PreventRepeat
    @ApiOperation("配置角色菜单")
    @PostMapping("/createRoleMenu")
    @RequiresPermissions("system:role:createRoleMenu")
    public ApiResponse createRoleMenu (@Validated @RequestBody CreateRoleMenuDTO dto) {
        systemRoleBiz.createRoleMenu(dto);
        return ApiResponse.success(null);
    }

    @PreventRepeat
    @ApiOperation("配置角色权限")
    @PostMapping("/createRolePermission")
    @RequiresPermissions("system:role:createRolePermission")
    public ApiResponse createRolePermission (@Validated @RequestBody CreateRolePermissionDTO dto) {
        systemRoleBiz.createRolePermission(dto);
        return ApiResponse.success(null);
    }

    @PreventRepeat
    @ApiOperation("新建")
    @PostMapping("/create")
    @RequiresPermissions("system:role:create")
    public ApiResponse create(@Validated(OperaType.Create.class) @RequestBody SystemRole systemRole) {
        return ApiResponse.success(systemRoleBiz.create(systemRole));
    }

    @ApiOperation("删除")
    @GetMapping("/delete/{id}")
    @RequiresPermissions("system:role:delete")
    public ApiResponse deleteById(@PathVariable Integer id) {
        systemRoleBiz.deleteById(id);
        return ApiResponse.success(null);
    }

    @ApiOperation("批量删除")
    @GetMapping("/delete/batch")
    @RequiresPermissions("system:role:delete")
    public ApiResponse deleteByIdInBatch(@RequestParam String ids) {
        String [] idArray = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String id : idArray) {
            idList.add(Integer.valueOf(id));
        }
        systemRoleBiz.deleteByIdInBatch(idList);
        return ApiResponse.success(null);
    }

    @ApiOperation("修改")
    @PostMapping("/updateById")
    @RequiresPermissions("system:role:update")
    public ApiResponse updateById(@Validated(OperaType.Update.class) @RequestBody SystemRole systemRole) {
        systemRoleBiz.updateById(systemRole);
        return ApiResponse.success(null);
    }

    @ApiOperation("分页查询")
    @PostMapping("/page")
    @RequiresPermissions("system:role:query")
    public ApiResponse<PageData<SystemRoleListVO>> findPage (@RequestBody PageWrap<QuerySystemRoleDTO> pageWrap) {
        return ApiResponse.success(systemRoleService.findPage(pageWrap));
    }

    @ApiOperation("查询所有")
    @GetMapping("/all")
    @RequiresPermissions("system:role:query")
    public ApiResponse<List<SystemRole>> findAll () {
        SystemRole systemRole = new SystemRole();
        systemRole.setDeleted(Boolean.FALSE);
        return ApiResponse.success(systemRoleService.findList(systemRole));
    }
}
