package com.raine.springboot.demo.dao;

import com.raine.springboot.demo.domain.User;
import com.raine.springboot.demo.domain.base.EntityDao;


/**
 * 用户表 Mapper
 * 
 * @author chenjun
 * @date 2020-04-29
 */
public interface IUserMapper extends EntityDao<User, Integer> {

    User findUserByMobile(String mobile);
}