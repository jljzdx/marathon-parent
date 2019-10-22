package com.newera.marathon.dto.cms.maintenance;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class XfaceCmsAdminUserModifyRequestDTO {


    @NotNull(message = "用户ID不能为空")
    private Integer id;

    @NotNull(message = "性别不能为空")
    private Integer gender;

    @NotBlank(message = "手机号不能为空")
    @Size(max = 15, message= "手机号长度不能超过15位")
    private String mobile;

    @NotBlank(message = "真实姓名不能为空")
    @Size(max = 255, message= "真实姓名长度不能超过255位")
    private String realName;

    @NotBlank(message = "邮箱不能为空")
    @Size(max = 255, message= "邮箱长度不能超过255位")
    private String email;

    @NotBlank(message = "更新人不能为空")
    @Size(max = 255, message= "更新人长度不能超过255位")
    private String modifyOperator;

    private String roles;
}
