package com.newera.marathon.dto.system.inquiry;

import lombok.Data;

import java.util.List;

@Data
public class XfaceSysRoleAuthInquiryResponseSubDTO {

    /**
     * 资源ID
     */
    private Integer id;

    /**
     * 资源名称
     */
    private String title;

    /**
     * 是否展开
     */
    private Boolean spread;

    /**
     * 是否禁用
     */
    private Boolean disabled;

    /**
     * 子列表
     */
    private List<XfaceSysRoleAuthInquiryResponseSubDTO> children;

}
