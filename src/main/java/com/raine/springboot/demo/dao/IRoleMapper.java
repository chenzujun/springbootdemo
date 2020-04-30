package com.raine.springboot.demo.dao;

import com.raine.springboot.demo.domain.Role;
import com.raine.springboot.demo.domain.base.EntityDao;
import com.raine.springboot.demo.domain.vo.RoleVo;

import java.util.List;


/**
 * Role Mapper
 * 
 * @author chenjun
 * @date 2020-04-29
 */
public interface IRoleMapper extends EntityDao<Role, Integer> {

    RoleVo findRoleAndPerms(Integer id);

    List<Role> getRoleByUserId(Integer userId);

}