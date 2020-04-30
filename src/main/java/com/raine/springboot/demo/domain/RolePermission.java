package com.raine.springboot.demo.domain;

import lombok.Data;

/**
 * RolePermission 实体类
 *
 * @author chenjun
 * @date 2020-04-29
 */
@Data
public class RolePermission implements java.io.Serializable {

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