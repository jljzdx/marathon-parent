package com.newera.marathon.dto.system.inquiry;

import lombok.Data;

import java.util.List;

@Data
public class XfaceSysResourceInquiryLoopResponseSubDTO {

    private Integer id;

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
    private String priority;

    /**
     * 资源类型
     */
    private String type;

    /**
     * 是否可用
     */
    private Integer available;

    /**
     * 子列表
     */
    private List<XfaceSysResourceInquiryLoopResponseSubDTO> child;

}
