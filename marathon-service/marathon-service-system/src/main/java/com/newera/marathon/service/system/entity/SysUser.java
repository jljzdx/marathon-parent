package com.newera.marathon.service.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_user")
public class SysUser implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String realName;

    private Integer sex;

    private String password;

    private String mobile;

    private String email;

    private Date lastLoginTime;

    private Date loginTime;

    private Integer loginCount;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModify;

    private String operator;
}
