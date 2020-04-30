package com.raine.springboot.demo.domain;

import java.util.Date;

import com.raine.springboot.demo.domain.base.BaseEntity;
import lombok.Data;

/**
 * Permission 实体类
 *
 * @author chenjun
 * @date 2020-04-29
 */
@Data
public class Permission extends BaseEntity implements java.io.Serializable {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单id
     */
    private Integer pid;

    /**
     * 菜单排序
     */
    private Integer zindex;

    /**
     * 权限分类（0 菜单；1 功能）
     */
    private Integer istype;

    /**
     * 描述
     */
    private String descpt;

    /**
     * 菜单编号
     */
    private String code;

    /**
     * 菜单图标名称
     */
    private String icon;

    /**
     * 菜单url
     */
    private String page;

    /**
     * 创建时间
     */
    private Date insertTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}