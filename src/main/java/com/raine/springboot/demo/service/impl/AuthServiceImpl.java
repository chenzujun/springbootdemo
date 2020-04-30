package com.raine.springboot.demo.service.impl;

import com.raine.springboot.demo.dao.IPermissionMapper;
import com.raine.springboot.demo.dao.IRoleMapper;
import com.raine.springboot.demo.domain.Permission;
import com.raine.springboot.demo.domain.Role;
import com.raine.springboot.demo.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenjun
 * @date 2020/4/29
 * @since V1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthServiceImpl implements IAuthService {


    @Autowired
    private IPermissionMapper permissionMapper;
    @Autowired
    private IRoleMapper roleMapper;

    @Override
    public List<Role> getRoleByUser(Integer userId) {
        return this.roleMapper.getRoleByUserId(userId);
    }

    @Override
    public List<Permission> findPermsByRoleId(Integer id) {
        return this.permissionMapper.findPermsByRole(id);
    }
}
