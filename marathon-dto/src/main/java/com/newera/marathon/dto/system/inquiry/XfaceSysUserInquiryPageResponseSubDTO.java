package com.newera.marathon.dto.system.inquiry;

import lombok.Data;

@Data
public class XfaceSysUserInquiryPageResponseSubDTO {

    private Integer id;

    private Integer sex;

    private String userName;

    private String realName;

    private String mobile;

    private String email;

    private String lastLoginTime;

    private String loginTime;

    private Integer loginCount;

    private Integer status;

    private String gmtCreate;

    private String roleNames;

}
