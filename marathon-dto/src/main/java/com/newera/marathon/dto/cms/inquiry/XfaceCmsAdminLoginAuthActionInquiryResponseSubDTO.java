package com.newera.marathon.dto.cms.inquiry;

import lombok.Data;

/**
 * 登陆后，查询当前用户功能权限列表
 */
@Data
public class XfaceCmsAdminLoginAuthActionInquiryResponseSubDTO {

    /**
     * 资源功能ID
     */
    private Integer actionId;

    /**
     * 权限码
     */
    private String permission;

}
