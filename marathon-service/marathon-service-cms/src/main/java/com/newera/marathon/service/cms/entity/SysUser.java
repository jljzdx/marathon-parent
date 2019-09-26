package com.newera.marathon.service.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.newera.marathon.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_user")
public class SysUser extends BaseEntity implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 后台登陆账号
     */
    private String userName;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 登陆密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 上一次登陆时间
     */
    private Date lastLoginTime;

    /**
     * 最后一次登陆时间
     */
    private Date loginTime;

    /**
     * 登陆次数
     */
    private Integer loginCount;

    /**
     * 是否锁定（0/1：锁定/未锁定）
     */
    private Integer locked;
}
