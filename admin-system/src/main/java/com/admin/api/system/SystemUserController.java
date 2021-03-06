package com.admin.api.system;

import com.admin.api.BaseController;
import com.admin.biz.system.SystemUserBiz;
import com.admin.core.annotation.pr.PreventRepeat;
import com.admin.core.annotation.trace.Trace;
import com.admin.core.model.ApiResponse;
import com.admin.core.constants.OperaType;
import com.admin.core.model.PageData;
import com.admin.core.model.PageWrap;
import com.admin.dao.system.dto.CreateSystemUserDTO;
import com.admin.dao.system.dto.CreateUserRoleDTO;
import com.admin.dao.system.dto.QuerySystemUserDTO;
import com.admin.dao.system.dto.ResetSystemUserPwdDTO;
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
@Api(tags = "用户")
@RestController
@RequestMapping("/system/user")
public class SystemUserController extends BaseController {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SystemUserBiz systemUserBiz;

    @PreventRepeat
    @ApiOperation("配置用户角色")
    @PostMapping("/createUserRole")
    @RequiresPermissions("system:user:createUserRole")
    public ApiResponse createUserRole (@Validated @RequestBody CreateUserRoleDTO dto) {
        systemUserBiz.createUserRole(dto);
        return ApiResponse.success(null);
    }

    @Trace(withRequestParameters = false)
    @PreventRepeat
    @ApiOperation("重置用户密码")
    @PostMapping("/resetPwd")
    @RequiresPermissions("system:user:resetPwd")
    public ApiResponse resetPwd (@Validated @RequestBody ResetSystemUserPwdDTO dto) {
        dto.setOperaUserId(this.getLoginUser().getId());
        systemUserBiz.resetPwd(dto);
        return ApiResponse.success(null);
    }

    @Trace(withRequestParameters = false)
    @PreventRepeat
    @ApiOperation("新建")
    @PostMapping("/create")
    @RequiresPermissions("system:user:create")
    public ApiResponse create(@Validated(OperaType.Create.class) @RequestBody CreateSystemUserDTO systemUser) {
        systemUser.setCreateUser(this.getLoginUser().getId());
        systemUserBiz.create(systemUser);
        return ApiResponse.success(null);
    }

    @ApiOperation("删除")
    @GetMapping("/delete/{id}")
    @RequiresPermissions("system:user:delete")
    public ApiResponse deleteById(@PathVariable Integer id) {
        systemUserBiz.deleteById(id);
        return ApiResponse.success(null);
    }

    @ApiOperation("批量删除")
    @GetMapping("/delete/batch")
    @RequiresPermissions("system:user:delete")
    public ApiResponse deleteByIdInBatch(@RequestParam String ids) {
        String [] idArray = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String id : idArray) {
            idList.add(Integer.valueOf(id));
        }
        systemUserBiz.deleteByIdInBatch(idList);
        return ApiResponse.success(null);
    }

    @Trace(withRequestParameters = false)
    @ApiOperation("修改")
    @PostMapping("/updateById")
    @RequiresPermissions("system:user:update")
    public ApiResponse updateById(@Validated(OperaType.Update.class) @RequestBody CreateSystemUserDTO systemUser) {
        systemUser.setUpdateUser(this.getLoginUser().getId());
        systemUserBiz.updateById(systemUser);
        return ApiResponse.success(null);
    }

    @ApiOperation("分页查询")
    @PostMapping("/page")
    @RequiresPermissions("system:user:query")
    public ApiResponse<PageData<SystemUserListVO>> findPage (@RequestBody PageWrap<QuerySystemUserDTO> pageWrap) {
        return ApiResponse.success(systemUserService.findPage(pageWrap));
    }
}
