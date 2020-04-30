package com.raine.springboot.demo.domain.vo;

import lombok.Data;

/**
 * RolePermission Vo 对象
 * 
 * @author chenjun
 * @date 2020-04-29
 */
@Data
public class RolePermissionVo {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * permitId 
     */
    private Integer permitId;

    /**
     * roleId 
     */
    private Integer roleId;


}