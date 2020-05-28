package com.newera.marathon.dto.cms.inquiry;

import lombok.Data;

import java.util.List;

@Data
public class XfaceCmsAdminLeftMenuInquiryResponseSubDTO {

    private Integer id;

    /**
     * 资源名称
     */
    private String name;

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
     * 子节点集合
     */
    private List<XfaceCmsAdminLeftMenuInquiryResponseSubDTO> children;
}
