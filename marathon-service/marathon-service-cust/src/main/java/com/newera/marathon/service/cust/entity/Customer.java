package com.newera.marathon.service.cust.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 客户信息表
 * </p>
 *
 * @author MicroBin
 * @since 2019-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("CUSTOMER")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 1/2/3：身份证/军官证/护照
     */
    private Integer idCardType;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 1/2：男/女
     */
    private Integer gender;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 1/2/3：WEB/ANDROID/IOS/H5
     */
    private Integer channel;

    /**
     * 0/1：未删除/已删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 创建人
     */
    private String createOperator;

    /**
     * 修改时间
     */
    private Date gmtModify;

    /**
     * 修改人
     */
    private String modifyOperator;


}
