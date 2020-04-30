package com.raine.springboot.demo.service;

import java.util.List;

import com.raine.springboot.demo.domain.Permission;
import com.raine.springboot.demo.domain.query.PermissionQuery;
import com.github.pagehelper.PageInfo;
import com.raine.springboot.demo.domain.vo.PermissionVo;

/**
 * Permission Service
 * 
 * @author chenjun
 * @date 2020-04-29
 */
public interface IPermissionService {

    /**
     * 根据id查询
     * 
     * @param id 主键编号
     * @return 
     */
    Permission getPermissionById(Integer id);

    /**
     * 根据组合条件查询
     *
     * @param query 查询对象
     * @return 集合,如果不存在,返回Empty List
     */
    List<Permission> searchPermission(PermissionQuery query);

    /**
     * 分页查询
     * 
     * @param query 查询对象
     * @return 分页结果,如果不存在,结果集为Empty List
     */
     PageInfo<Permission> pagePermission(PermissionQuery query);

    /**
     * 保存
     * 
     * @param permission 
     * @return 操作影响行数
     */
    int savePermission(Permission permission);

    /**
     * 批量保存
     * 
     * @param permissions 
     * @return 操作影响行数
     */
    int savePermissions(List<Permission> permissions);

    /**
     * 局部更新
     *
     * @param permission 
     * @return 操作影响行数
     */
    int updatePartPermission(Permission permission);

    /**
     * 根据编号删除
     *
     * @param id 编号
     * @return 操作影响行数
     */
    int deletePermission(Integer id);

    /**
     * 根据id集合批量删除
     *
     * @param ids id主键集合
     * @return 操作影响行数
     */
    int deletePermissions(List<Integer> ids);

    List<PermissionVo> getPermissionByUserId(Integer userId);
}
