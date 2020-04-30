package com.raine.springboot.demo.domain.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * Role Vo 对象
 * 
 * @author chenjun
 * @date 2020-04-29
 */
@Data
public class RoleVo {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * id 
     */
    private Integer id;

    /**
     * 角色名称 
     */
    private String roleName;

    /**
     * 角色描述 
     */
    private String descpt;

    /**
     * 角色编号 
     */
    private String code;

    /**
     * 操作用户id 
     */
    private Integer insertUid;

    /**
     * 创建时间 
     */
    private Date insertTime;

    /**
     * 修改时间 
     */
    private Date updateTime;

    private List<RolePermissionVo> rolePerms;


}