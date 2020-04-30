package com.raine.springboot.demo.domain;

import java.util.Date;

import com.raine.springboot.demo.domain.base.BaseEntity;
import lombok.Data;

/**
 * Role 实体类
 *
 * @author chenjun
 * @date 2020-04-29
 */
@Data
public class Role extends BaseEntity implements java.io.Serializable {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

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


}