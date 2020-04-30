package com.raine.springboot.demo.dao;

import com.raine.springboot.demo.domain.Permission;
import com.raine.springboot.demo.domain.base.EntityDao;
import com.raine.springboot.demo.domain.vo.PermissionVo;

import java.util.List;


/**
 * Permission Mapper
 * 
 * @author chenjun
 * @date 2020-04-29
 */
public interface IPermissionMapper extends EntityDao<Permission, Integer> {

    /**
     * 查找所有子节点
     * @param pid
     * @return
     */
    List<Permission> findChildPerm(int pid);

    /**
     * 查询权限树列表
     * @return
     */
    List<PermissionVo> findPerms();

    List<PermissionVo> getUserPerms(Integer userId);

    /**
     * 根据角色id获取权限数据
     * @param roleId
     * @return
     */
    List<Permission> findPermsByRole(Integer roleId);

}