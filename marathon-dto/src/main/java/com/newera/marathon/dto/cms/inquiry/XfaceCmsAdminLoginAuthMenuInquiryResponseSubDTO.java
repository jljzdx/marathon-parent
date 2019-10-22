package com.newera.marathon.dto.cms.inquiry;

import lombok.Data;

/**
 * 登陆后，查询当前用户左侧菜单列表
 */
@Data
public class XfaceCmsAdminLoginAuthMenuInquiryResponseSubDTO {

    /**
     * 资源菜单ID
     */
    private Integer menuId;

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

}
