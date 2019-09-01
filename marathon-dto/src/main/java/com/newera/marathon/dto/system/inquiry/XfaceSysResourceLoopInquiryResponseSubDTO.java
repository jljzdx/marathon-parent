package com.newera.marathon.dto.system.inquiry;

import lombok.Data;

@Data
public class XfaceSysResourceLoopInquiryResponseSubDTO {

    private Integer id;

    /**
     * 父节点ID
     */
    private Integer parentId;

    /**
     * 资源名称
     */
    private String name;

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
     * 资源类型
     */
    private Integer type;

    /**
     * 是否可用
     */
    private Integer available;

}
