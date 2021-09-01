package com.admin.api.system;

import com.admin.api.BaseController;
import com.admin.biz.system.SystemDepartmentBiz;
import com.admin.core.annotation.pr.PreventRepeat;
import com.admin.core.annotation.trace.Trace;
import com.admin.core.model.ApiResponse;
import com.admin.core.constants.OperaType;
import com.admin.core.model.PageData;
import com.admin.core.model.PageWrap;
import com.admin.dao.system.dto.QuerySystemUserDTO;
import com.admin.dao.system.model.SystemDepartment;
import com.admin.dao.system.vo.SystemDepartmentListVO;
import com.admin.dao.system.vo.SystemUserListVO;
import com.admin.service.system.SystemUserService;
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
@Api(tags = "部门")
@RestController
@RequestMapping("/system/department")
public class SystemDepartmentController extends BaseController {

    @Autowired
    private SystemDepartmentBiz systemDepartmentBiz;

    @Autowired
    private SystemUserService systemUserService;

    @PreventRepeat
    @ApiOperation("新建")
    @PostMapping("/create")
    @RequiresPermissions("system:department:create")
    public ApiResponse create(@Validated(OperaType.Create.class) @RequestBody SystemDepartment systemDepartment) {
        return ApiResponse.success(systemDepartmentBiz.create(systemDepartment));
    }

    @ApiOperation("删除")
    @GetMapping("/delete/{id}")
    @RequiresPermissions("system:department:delete")
    public ApiResponse deleteById(@PathVariable Integer id) {
        systemDepartmentBiz.deleteById(id);
        return ApiResponse.success(null);
    }

    @ApiOperation("批量删除")
    @GetMapping("/delete/batch")
    @PreventRepeat
    @RequiresPermissions("system:department:delete")
    public ApiResponse deleteByIdInBatch(@RequestParam String ids) {
        String [] idArray = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String id : idArray) {
            idList.add(Integer.valueOf(id));
        }
        systemDepartmentBiz.deleteByIdInBatch(idList);
        return ApiResponse.success(null);
    }

    @ApiOperation("修改")
    @PostMapping("/updateById")
    @RequiresPermissions("system:department:update")
    public ApiResponse updateById(@Validated(OperaType.Update.class) @RequestBody SystemDepartment systemDepartment) {
        systemDepartmentBiz.updateById(systemDepartment);
        return ApiResponse.success(null);
    }

    @ApiOperation("查询部门列表")
    @PostMapping("/tree")
    @RequiresPermissions("system:department:query")
    public ApiResponse<List<SystemDepartmentListVO>> findTree () {
        return ApiResponse.success(systemDepartmentBiz.findTree());
    }

    @Trace(exclude = true)
    @ApiOperation("查询部门人员")
    @PostMapping("/users")
    @RequiresPermissions("system:department:queryUsers")
    public ApiResponse<PageData<SystemUserListVO>> findPage (@RequestBody PageWrap<QuerySystemUserDTO> pageWrap) {
        return ApiResponse.success(systemUserService.findPage(pageWrap));
    }
}
