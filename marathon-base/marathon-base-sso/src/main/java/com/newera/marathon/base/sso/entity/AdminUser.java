package com.newera.marathon.base.sso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.newera.marathon.common.model.BaseEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
@TableName("CMS_ADMIN_USER")
public class AdminUser extends BaseEntity implements UserDetails
{

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 后台登陆账号
     */
    private String username;

    /**
     * 真实名称
     */
    private String realName;
    /**
     * 昵称
     */
    private String nickName;

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


    /**
     * 帐户是否过期(true(1) 未过期，false(0)已过期)
     * 设置默认值为true，新增用户默认未过期
     *
     * 注意：生成的setter和getter方法没有 `is`
     *        setAccountNonExpired
     *        getAccountNonExpired
     *   所以前端获取时也不要有 `is`
     */
    private boolean isAccountNonExpired = true;
    /**
     * 帐户是否被锁定(true(1) 未过期，false(0)已过期)
     * 设置默认值为true，新增用户默认未过期
     */
    private boolean isAccountNonLocked = true;
    /**
     * 密码是否过期(true(1) 未过期，false(0)已过期)
     * 设置默认值为true，新增用户默认未过期
     */
    private boolean isCredentialsNonExpired = true;
    /**
     * 帐户是否可用(true(1) 可用，false(0)未删除)
     * 设置默认值为true，新增用户默认未过期
     */
    private boolean isEnabled = true;

    /**
     * 拥有权限集合
     */
    @TableField(exist = false) //该属性不是数据库表字段
    private Collection<? extends GrantedAuthority> authorities;

    // 父接口认证方法 start
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
