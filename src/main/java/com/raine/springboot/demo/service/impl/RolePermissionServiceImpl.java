package com.raine.springboot.demo.service.impl;

import com.raine.springboot.demo.service.IRolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * RolePermission Service实现
 * 
 * @author chenjun
 * @date 2020-04-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RolePermissionServiceImpl implements IRolePermissionService {

}
