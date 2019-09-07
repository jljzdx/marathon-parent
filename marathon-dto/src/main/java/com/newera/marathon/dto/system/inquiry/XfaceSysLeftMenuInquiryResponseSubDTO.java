package com.newera.marathon.dto.system.inquiry;

import lombok.Data;

import java.util.List;

@Data
public class XfaceSysLeftMenuInquiryResponseSubDTO {

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
     * 子节点集合
     */
    private List<XfaceSysLeftMenuInquiryResponseSubDTO> children;
}
