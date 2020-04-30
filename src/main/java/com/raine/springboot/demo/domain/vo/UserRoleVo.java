package com.raine.springboot.demo.domain.vo;

import lombok.Data;

/**
 * UserRole Vo 对象
 * 
 * @author chenjun
 * @date 2020-04-29
 */
@Data
public class UserRoleVo {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * userId 
     */
    private Integer userId;

    /**
     * roleId 
     */
    private Integer roleId;


}