package com.newera.marathon.dto.cms.inquiry;

import lombok.Data;

import java.util.List;

@Data
public class XfaceCmsAdminRoleAuthInquiryResponseSubDTO {

    /**
     * 资源ID
     */
    private Integer value;

    /**
     * 资源名称
     */
    private String name;


    /**
     * 是否选中
     */
    private Boolean checked;

    /**
     * 是否禁用
     */
    private Boolean disabled;

    /**
     * 子列表
     */
    private List<XfaceCmsAdminRoleAuthInquiryResponseSubDTO> list;

}
