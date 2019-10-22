package com.newera.marathon.dto.cms.inquiry;

import lombok.Data;

@Data
public class XfaceCmsAdminUserInquiryPageResponseSubDTO {

    private Integer id;

    private Integer gender;

    private String userName;

    private String realName;

    private String mobile;

    private String email;

    private String lastLoginTime;

    private String loginTime;

    private Integer loginCount;

    private Integer locked;

    private String gmtCreate;

    private String roleNames;

}
