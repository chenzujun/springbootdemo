package com.raine.springboot.demo.domain;

import lombok.Data;

/**
 * UserRole 实体类
 *
 * @author chenjun
 * @date 2020-04-29
 */
@Data
public class UserRole implements java.io.Serializable {

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