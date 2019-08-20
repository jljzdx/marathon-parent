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
    private String name;

    /**
     * 是否选中
     */
    private Boolean checked;

    /**
     * 是否展开
     */
    private Boolean spread;

    /**
     * 是否禁用
     */
    private Integer available;

    /**
     * 子列表
     */
    private List<XfaceSysRoleAuthInquiryResponseSubDTO> child;

}
