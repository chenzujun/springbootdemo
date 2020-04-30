package com.raine.springboot.demo.service.impl;

import java.util.List;

import com.raine.springboot.demo.domain.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.raine.springboot.demo.domain.Permission;
import com.raine.springboot.demo.service.IPermissionService;
import com.raine.springboot.demo.dao.IPermissionMapper;
import com.raine.springboot.demo.domain.query.PermissionQuery;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Permission Service实现
 * 
 * @author chenjun
 * @date 2020-04-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionMapper permissionMapper;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Permission getPermissionById(Integer id) {
        return permissionMapper.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Permission> searchPermission(PermissionQuery query) {
        return permissionMapper.search(query);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PageInfo<Permission> pagePermission(PermissionQuery query) {
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        List<Permission> list = searchPermission(query);
        PageInfo<Permission> page = new PageInfo<Permission>(list);
        return page;
    }

    @Override
    public int savePermission(Permission permission) {
        return permissionMapper.save(permission);
    }

    @Override
    public int savePermissions(List<Permission> permissions) {
        return permissionMapper.save(permissions);
    }

    @Override
    public int updatePartPermission(Permission permission) {
        return permissionMapper.updatePart(permission);
    }

    @Override
    public int deletePermission(Integer id) {
        return permissionMapper.deleteByPk(id);
    }

    @Override
    public int deletePermissions(List<Integer> ids) {
        return permissionMapper.deleteBatchByPks(ids);
    }

    @Override
    public List<PermissionVo> getPermissionByUserId(Integer userId) {
        return permissionMapper.getUserPerms(userId);
    }
}
