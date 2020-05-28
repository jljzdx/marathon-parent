package com.newera.marathon.service.cms.vo;

import lombok.Data;

@Data
public class LeftMenuListVO {

    private Integer id;

    /**
     * 父节点ID
     */
    private Integer parentId;

    private String parentIds;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 显示顺序
     */
    private String priority;

    /**
     * 访问路径
     */
    private String url;

    /**
     * 类型
     */
    private Integer type;
}
