package com.raine.springboot.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.raine.springboot.demo.domain.Role;
import com.raine.springboot.demo.service.IRoleService;
import com.raine.springboot.demo.dao.IRoleMapper;
import com.raine.springboot.demo.domain.query.RoleQuery;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Role Service实现
 * 
 * @author chenjun
 * @date 2020-04-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleMapper roleMapper;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Role getRoleById(Integer id) {
        return roleMapper.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Role> searchRole(RoleQuery query) {
        return roleMapper.search(query);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PageInfo<Role> pageRole(RoleQuery query) {
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        List<Role> list = searchRole(query);
        PageInfo<Role> page = new PageInfo<Role>(list);
        return page;
    }

    @Override
    public int saveRole(Role role) {
        return roleMapper.save(role);
    }

    @Override
    public int saveRoles(List<Role> roles) {
        return roleMapper.save(roles);
    }

    @Override
    public int updatePartRole(Role role) {
        return roleMapper.updatePart(role);
    }

    @Override
    public int deleteRole(Integer id) {
        return roleMapper.deleteByPk(id);
    }

    @Override
    public int deleteRoles(List<Integer> ids) {
        return roleMapper.deleteBatchByPks(ids);
    }

}
