package com.admin.biz.system;

import com.admin.dao.system.dto.CreateSystemUserDTO;
import com.admin.dao.system.dto.CreateUserRoleDTO;
import com.admin.dao.system.dto.ResetSystemUserPwdDTO;
import com.admin.dao.system.dto.UpdatePwdDto;

import java.util.List;

/**
 * 系统用户业务处理
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
public interface SystemUserBiz {

    /**
     * 删除
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void deleteById(Integer id);

    /**
     * 批量删除
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void deleteByIdInBatch(List<Integer> ids);

    /**
     * 修改密码
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updatePwd(UpdatePwdDto dto);

    /**
     * 重置密码
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void resetPwd(ResetSystemUserPwdDTO dto);

    /**
     * 创建用户
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void create(CreateSystemUserDTO systemUser);

    /**
     * 修改用户信息
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void updateById(CreateSystemUserDTO systemUser);

    /**
     * 创建用户角色
     * @author Eva.Caesar Liu
     * @date 2021/08/31 21:16
     */
    void createUserRole(CreateUserRoleDTO dto);
}
