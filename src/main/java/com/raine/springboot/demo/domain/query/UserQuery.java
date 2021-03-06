package com.raine.springboot.demo.domain.query;

import java.util.Date;

import com.raine.springboot.demo.domain.base.BaseQuery;
import lombok.Data;

/**
 * 用户表 查询对象
 *
 * @author chenjun
 * @date 2020-04-29
 */
@Data
public class UserQuery extends BaseQuery {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 添加该用户的用户id
     */
    private Integer insertUid;

    /**
     * 注册时间
     */
    private Date insertTimeBegin;

    /**
     * 注册时间
     */
    private Date insertTimeEnd;

    /**
     * 修改时间
     */
    private Date updateTimeBegin;

    /**
     * 修改时间
     */
    private Date updateTimeEnd;

    /**
     * 是否删除（0：正常；1：已删）
     */
    private Boolean isDel;

    /**
     * 是否在职（0：正常；1，离职）
     */
    private Boolean isJob;

    /**
     * 短信验证码
     */
    private String mcode;

    /**
     * 短信发送时间
     */
    private Date sendTimeBegin;

    /**
     * 短信发送时间
     */
    private Date sendTimeEnd;

    /**
     * 更新版本
     */
    private Integer version;


}
