package com.newera.marathon.service.cms.entity;

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
 * 
 * </p>
 *
 * @author MicroBin
 * @since 2019-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("CMS_ADMIN_ROLE_RESOURCE")
public class AdminRoleResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer roleId;

    private Integer resourceId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 创建人
     */
    private String createOperator;

    /**
     * 更新时间
     */
    private Date gmtModify;

    /**
     * 更新人
     */
    private String modifyOperator;


}
