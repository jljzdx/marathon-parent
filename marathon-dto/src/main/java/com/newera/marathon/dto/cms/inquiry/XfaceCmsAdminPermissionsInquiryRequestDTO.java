package com.newera.marathon.dto.cms.inquiry;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class XfaceCmsAdminPermissionsInquiryRequestDTO {

    /**
     * 系统ID【预留】
     */
    //@NotNull(message = "系统ID不能为空")
    private Integer systemId;
    /**
     * 登陆用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Size(max = 255, message= "用户名长度不能超过255位")
    private String userName;
}
