package com.admin.api.system;

import com.admin.api.BaseController;
import com.admin.biz.system.SystemMenuBiz;
import com.admin.core.annotation.pr.PreventRepeat;
import com.admin.core.model.ApiResponse;
import com.admin.core.constants.OperaType;
import com.admin.dao.system.dto.UpdateSystemMenuSortDTO;
import com.admin.dao.system.model.SystemMenu;
import com.admin.dao.system.vo.SystemMenuListVO;
import com.admin.dao.system.vo.SystemMenuNodeVO;
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
@Api(tags = "系统菜单")
@RestController
@RequestMapping("/system/menu")
public class SystemMenuController extends BaseController {

    @Autowired
    private SystemMenuBiz systemMenuBiz;

    @ApiOperation("菜单排序")
    @PostMapping("/updateSort")
    @RequiresPermissions("system:menu:sort")
    public ApiResponse updateSort (@Validated @RequestBody UpdateSystemMenuSortDTO dto) {
        systemMenuBiz.updateSort(dto);
        return ApiResponse.success(null);
    }

    @ApiOperation("查询菜单树")
    @GetMapping("/treeNodes")
    public ApiResponse<List<SystemMenuNodeVO>> getTreeMenu () {
        return ApiResponse.success(systemMenuBiz.findTree(this.getLoginUser().getId()));
    }

    @ApiOperation("查询列表树")
    @PostMapping("/treeList")
    @RequiresPermissions("system:menu:query")
    public ApiResponse<List<SystemMenuListVO>> findTree () {
        return ApiResponse.success(systemMenuBiz.findTree());
    }

    @PreventRepeat
    @ApiOperation("新建")
    @PostMapping("/create")
    @RequiresPermissions("system:menu:create")
    public ApiResponse create(@Validated(OperaType.Create.class) @RequestBody SystemMenu systemMenu) {
        return ApiResponse.success(systemMenuBiz.create(systemMenu));
    }

    @ApiOperation("删除")
    @GetMapping("/delete/{id}")
    @RequiresPermissions("system:menu:delete")
    public ApiResponse deleteById(@PathVariable Integer id) {
        systemMenuBiz.deleteById(id);
        return ApiResponse.success(null);
    }

    @ApiOperation("批量删除")
    @GetMapping("/delete/batch")
    @RequiresPermissions("system:menu:delete")
    public ApiResponse deleteByIdInBatch(@RequestParam String ids) {
        String [] idArray = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String id : idArray) {
            idList.add(Integer.valueOf(id));
        }
        systemMenuBiz.deleteByIdInBatch(idList);
        return ApiResponse.success(null);
    }

    @ApiOperation("修改")
    @PostMapping("/updateById")
    @RequiresPermissions("system:menu:update")
    public ApiResponse updateById(@Validated(OperaType.Update.class) @RequestBody SystemMenu systemMenu) {
        systemMenuBiz.updateById(systemMenu);
        return ApiResponse.success(null);
    }

    @ApiOperation("修改菜单状态")
    @PostMapping("/updateStatus")
    @RequiresPermissions("system:menu:update")
    public ApiResponse updateStatus(@Validated(OperaType.UpdateStatus.class) @RequestBody SystemMenu systemMenu) {
        systemMenuBiz.updateById(systemMenu);
        return ApiResponse.success(null);
    }

}
