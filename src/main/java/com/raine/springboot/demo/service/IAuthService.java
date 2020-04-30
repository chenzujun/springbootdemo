package com.raine.springboot.demo.service;

import com.raine.springboot.demo.domain.Permission;
import com.raine.springboot.demo.domain.Role;

import java.util.List;

/**
 * 权限验证
 *
 * @author chenjun
 * @date 2020/4/29
 * @since V1.0.0
 */
public interface IAuthService {
    /**
     * 根据用户获取角色列表
     * @param userId
     * @return
     */
    List<Role> getRoleByUser(Integer userId);

    /**
     * 根据角色id获取权限数据
     * @param id
     * @return
     */
    List<Permission> findPermsByRoleId(Integer id);

}
