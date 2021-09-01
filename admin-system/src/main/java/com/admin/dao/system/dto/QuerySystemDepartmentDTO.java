package com.admin.dao.system.dto;

import lombok.Data;

import java.util.List;

/**
 * 查询系统部门参数
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Data
public class QuerySystemDepartmentDTO {

    private Integer id;

    private List<Integer> ids;
}
