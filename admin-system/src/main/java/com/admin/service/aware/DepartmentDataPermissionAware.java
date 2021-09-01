package com.admin.service.aware;

import com.admin.core.aware.DataPermissionMapping;
import com.admin.core.aware.DefaultDataPermissionAware;
import com.admin.core.constants.DataPermissionConstants;
import com.admin.dao.system.dto.QuerySystemDepartmentDTO;
import com.admin.dao.system.model.SystemDepartment;
import com.admin.dao.system.model.SystemDepartmentUser;
import com.admin.dao.system.vo.SystemDepartmentListVO;
import com.admin.service.system.SystemDepartmentService;
import com.admin.service.system.SystemDepartmentUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 部门数据权限控制
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Component
public class DepartmentDataPermissionAware extends DefaultDataPermissionAware<SystemDepartmentListVO> {

    @Autowired
    private SystemDepartmentService systemDepartmentService;

    @Autowired
    private SystemDepartmentUserService systemDepartmentUserService;

    @Override
    public DataPermissionConstants.Module module() {
        return DataPermissionConstants.Module.DEPARTMENT;
    }

    @Override
    public List<SystemDepartmentListVO> defaultData(Integer userId) {
        return onlyUser(userId);
    }

    /**
     * 全部数据
     *
     * @return List<SystemDepartmentListVO>
     */
    @DataPermissionMapping(value = DataPermissionConstants.Type.ALL, priority = 1)
    public List<SystemDepartmentListVO> all() {
        QuerySystemDepartmentDTO dto = new QuerySystemDepartmentDTO();
        return this.getRootList(systemDepartmentService.findList(dto));
    }

    /**
     * 自定义
     *
     * @param customData 自定义数据ID集
     * @return List<SystemDepartmentListVO>
     */
    @DataPermissionMapping(value = DataPermissionConstants.Type.DEPARTMENT_CUSTOM, priority = 2, injectCustomData = true)
    public List<SystemDepartmentListVO> custom(String customData) {
        if (StringUtils.isBlank(customData)) {
            return Collections.emptyList();
        }
        List<Integer> ids = new ArrayList<>();
        String[] stringIds = customData.split(",");
        for(String stringId : stringIds) {
            ids.add(Integer.valueOf(stringId));
        }
        QuerySystemDepartmentDTO dto = new QuerySystemDepartmentDTO();
        dto.setIds(ids);
        List<SystemDepartmentListVO> departmentListVo = systemDepartmentService.findList(dto);
        return this.getRootList(departmentListVo);
    }

    /**
     * 用户所属部门及其子孙部门
     *
     * @param userId 用户ID
     * @return List<SystemDepartmentListVO>
     */
    @DataPermissionMapping(value = DataPermissionConstants.Type.DEPARTMENT_CHILDREN, priority = 3, injectUser = true)
    public List<SystemDepartmentListVO> children(Integer userId) {
        return this.getRootList(getUserChildren(userId));
    }

    /**
     * 用户所属部门及其子部门
     *
     * @param userId 用户ID
     * @return List<SystemDepartmentListVO>
     */
    @DataPermissionMapping(value = DataPermissionConstants.Type.DEPARTMENT_CHILD, priority = 4, injectUser = true)
    public List<SystemDepartmentListVO> child(Integer userId) {
        List<SystemDepartmentListVO> children = this.getRootList(getUserChildren(userId));
        for (SystemDepartmentListVO root : children) {
            if (CollectionUtils.isEmpty(root.getChildren())) {
                continue;
            }
            for (SystemDepartmentListVO child : root.getChildren()) {
                if (CollectionUtils.isEmpty(child.getChildren())) {
                    continue;
                }
                child.setHasChildren(Boolean.TRUE);
                child.setChildren(null);
            }
        }
        return children;
    }

    /**
     * 仅用户所属部门
     *
     * @param userId 用户ID
     * @return List<SystemDepartmentListVO>
     */
    @DataPermissionMapping(value = DataPermissionConstants.Type.DEPARTMENT, priority = 5, injectUser = true)
    public List<SystemDepartmentListVO> onlyUser(Integer userId) {
        SystemDepartmentListVO userDepartment = this.getUserDepartment(userId);
        if (userDepartment == null) {
            return Collections.emptyList();
        }
        return new ArrayList<SystemDepartmentListVO>(){{
            this.add(userDepartment);
        }};
    }

    /**
     * 获取根部门
     */
    private List<SystemDepartmentListVO> getRootList(List<SystemDepartmentListVO> departments) {
        if (CollectionUtils.isEmpty(departments)) {
            return Collections.emptyList();
        }
        List<SystemDepartmentListVO> rootDepartments = new ArrayList<>();
        // 添加根部门
        for (SystemDepartmentListVO currentDepartment : departments) {
            boolean hasParent = false;
            for (SystemDepartmentListVO department: departments) {
                if (department.getId().equals(currentDepartment.getParentId())) {
                    hasParent = true;
                    break;
                }
            }
            if (!hasParent) {
                rootDepartments.add(currentDepartment);
            }
        }
        // 移除根部门
        for (SystemDepartmentListVO rootDepartment : rootDepartments) {
            departments.removeIf(department -> department.getId().equals(rootDepartment.getId()));
        }
        // 填充子部门
        for (SystemDepartmentListVO child : rootDepartments) {
            this.fillChildren(child, departments);
        }
        return rootDepartments;
    }

    /**
     * 获取用户部门
     */
    private SystemDepartmentListVO getUserDepartment (Integer userId) {
        SystemDepartmentUser queryDto = new SystemDepartmentUser();
        queryDto.setUserId(userId);
        queryDto.setDeleted(Boolean.FALSE);
        SystemDepartmentUser departmentUser = systemDepartmentUserService.findOne(queryDto);
        if (departmentUser == null) {
            return null;
        }
        QuerySystemDepartmentDTO dto = new QuerySystemDepartmentDTO();
        dto.setId(departmentUser.getDepartmentId());
        List<SystemDepartmentListVO> systemDepartments = systemDepartmentService.findList(dto);
        if (systemDepartments.isEmpty()) {
            return null;
        }
        return systemDepartments.get(0);
    }

    /**
     * 获取用户子孙部门
     */
    private List<SystemDepartmentListVO> getUserChildren (Integer userId) {
        SystemDepartmentListVO userDepartment = this.getUserDepartment(userId);
        if (userDepartment == null) {
            return Collections.emptyList();
        }
        // 查询用户所在部门以下部门
        List<SystemDepartmentListVO> departmentListVo = new ArrayList<>();
        List<Integer> ids = systemDepartmentService.findChildren(userDepartment.getId());
        if (!ids.isEmpty()) {
            QuerySystemDepartmentDTO dto = new QuerySystemDepartmentDTO();
            dto.setIds(ids);
            departmentListVo = systemDepartmentService.findList(dto);
        }
        departmentListVo.add(userDepartment);
        return departmentListVo;
    }

    /**
     * 填充子部门
     */
    private void fillChildren(SystemDepartmentListVO parent, List<SystemDepartmentListVO> departments) {
        if (departments.size() == 0) {
            return;
        }
        if (parent.getChildren() == null) {
            parent.setChildren(new ArrayList<>());
        }
        List<Integer> handledIds = new ArrayList<>();
        for (SystemDepartmentListVO department : departments) {
            if (parent.getId().equals(department.getParentId())) {
                SystemDepartmentListVO child = new SystemDepartmentListVO();
                BeanUtils.copyProperties(department, child, "children");
                child.setChildren(new ArrayList<>());
                parent.getChildren().add(child);
                handledIds.add(department.getId());
            }
        }
        departments.removeIf(menu -> handledIds.contains(menu.getId()));
        parent.setHasChildren(Boolean.TRUE);
        if (parent.getChildren().size() > 0) {
            parent.setHasChildren(Boolean.FALSE);
            for (SystemDepartmentListVO child : parent.getChildren()) {
                this.fillChildren(child, departments);
            }
        }
    }
}
