package com.admin.api.system;

import com.admin.api.BaseController;
import com.admin.biz.system.SystemDictBiz;
import com.admin.core.annotation.pr.PreventRepeat;
import com.admin.core.model.ApiResponse;
import com.admin.core.constants.OperaType;
import com.admin.core.model.PageData;
import com.admin.core.model.PageWrap;
import com.admin.dao.system.dto.QuerySystemDictDTO;
import com.admin.dao.system.model.SystemDict;
import com.admin.dao.system.vo.SystemDictListVO;
import com.admin.service.system.SystemDictService;
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
@Api(tags = "系统字典")
@RestController
@RequestMapping("/system/dict")
public class SystemDictController extends BaseController {

    @Autowired
    private SystemDictService systemDictService;

    @Autowired
    private SystemDictBiz systemDictBiz;

    @PreventRepeat
    @ApiOperation("新建")
    @PostMapping("/create")
    @RequiresPermissions("system:dict:create")
    public ApiResponse create(@Validated(OperaType.Create.class) @RequestBody SystemDict systemDict) {
        return ApiResponse.success(systemDictBiz.create(systemDict));
    }

    @ApiOperation("删除")
    @GetMapping("/delete/{id}")
    @RequiresPermissions("system:dict:delete")
    public ApiResponse deleteById(@PathVariable Integer id) {
        systemDictService.deleteById(id);
        return ApiResponse.success(null);
    }

    @ApiOperation("批量删除")
    @GetMapping("/delete/batch")
    @RequiresPermissions("system:dict:delete")
    public ApiResponse deleteByIdInBatch(@RequestParam String ids) {
        String [] idArray = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String id : idArray) {
            idList.add(Integer.valueOf(id));
        }
        systemDictService.deleteByIdInBatch(idList);
        return ApiResponse.success(null);
    }

    @ApiOperation("修改")
    @PostMapping("/updateById")
    @RequiresPermissions("system:dict:update")
    public ApiResponse updateById(@Validated(OperaType.Update.class) @RequestBody SystemDict systemDict) {
        systemDictBiz.updateById(systemDict);
        return ApiResponse.success(null);
    }

    @ApiOperation("分页查询")
    @PostMapping("/page")
    @RequiresPermissions("system:dict:query")
    public ApiResponse<PageData<SystemDictListVO>> findPage (@RequestBody PageWrap<QuerySystemDictDTO> pageWrap) {
        return ApiResponse.success(systemDictService.findPage(pageWrap));
    }
}
