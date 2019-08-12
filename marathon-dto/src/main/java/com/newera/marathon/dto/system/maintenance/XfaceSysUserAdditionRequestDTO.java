package com.newera.marathon.dto.system.maintenance;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class XfaceSysUserAdditionRequestDTO {


    @NotNull(message = "用户名不能为空")
    private Integer sex;

    @NotBlank(message = "用户名不能为空")
    @Size(max = 75, message= "用户名长度不能超过75位")
    private String userName;

    @NotBlank(message = "手机号不能为空")
    @Size(max = 15, message= "手机号长度不能超过15位")
    private String mobile;

    @NotBlank(message = "真实姓名不能为空")
    @Size(max = 75, message= "真实姓名长度不能超过75位")
    private String realName;

    @NotBlank(message = "邮箱不能为空")
    @Size(max = 75, message= "邮箱长度不能超过75位")
    private String email;

    private String roles;
}
