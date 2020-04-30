package com.raine.springboot.demo.service;

import java.util.List;

import com.raine.springboot.demo.domain.Role;
import com.raine.springboot.demo.domain.query.RoleQuery;
import com.github.pagehelper.PageInfo;

/**
 * Role Service
 * 
 * @author chenjun
 * @date 2020-04-29
 */
public interface IRoleService {

    /**
     * 根据id查询
     * 
     * @param id 主键编号
     * @return 
     */
    Role getRoleById(Integer id);

    /**
     * 根据组合条件查询
     *
     * @param query 查询对象
     * @return 集合,如果不存在,返回Empty List
     */
    List<Role> searchRole(RoleQuery query);

    /**
     * 分页查询
     * 
     * @param query 查询对象
     * @return 分页结果,如果不存在,结果集为Empty List
     */
     PageInfo<Role> pageRole(RoleQuery query);

    /**
     * 保存
     * 
     * @param role 
     * @return 操作影响行数
     */
    int saveRole(Role role);

    /**
     * 批量保存
     * 
     * @param roles 
     * @return 操作影响行数
     */
    int saveRoles(List<Role> roles);

    /**
     * 局部更新
     *
     * @param role 
     * @return 操作影响行数
     */
    int updatePartRole(Role role);

    /**
     * 根据编号删除
     *
     * @param id 编号
     * @return 操作影响行数
     */
    int deleteRole(Integer id);

    /**
     * 根据id集合批量删除
     *
     * @param ids id主键集合
     * @return 操作影响行数
     */
    int deleteRoles(List<Integer> ids);
}
