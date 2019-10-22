package com.newera.marathon.service.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.newera.marathon.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author MicroBin
 * @since 2019-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("CMS_ADMIN_RESOURCE")
public class AdminResource extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 父编号
     */
    private Integer parentId;

    /**
     * 父编号列表
     */
    private String parentIds;

    /**
     * 权限码
     */
    private String permission;

    /**
     * 图标
     */
    private String icon;

    /**
     * 访问路径
     */
    private String url;

    /**
     * 显示顺序
     */
    private Integer priority;

    /**
     * 资源类型（1：菜单；2：功能）
     */
    private Integer type;

    /**
     * 是否可用（0:不可用；1：可用）
     */
    private Integer available;

}
