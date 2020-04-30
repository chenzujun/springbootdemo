package com.raine.springboot.demo.service.impl;

import com.raine.springboot.demo.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserRole Service实现
 * 
 * @author chenjun
 * @date 2020-04-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl implements IUserRoleService {

}
