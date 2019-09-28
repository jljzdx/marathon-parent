package com.newera.marathon.dto.cust.maintenance;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class XfaceCustCustomerRegisterRequestDTO {

    @ApiModelProperty(value = "用户名",required = true)
    @NotBlank(message = "用户名不能为空")
    @Size(max = 20, message= "用户名长度不能超过20位")
    private String userName;

    @ApiModelProperty(value = "电话",required = true)
    @NotBlank(message = "电话不能为空")
    @Size(max = 11, message= "电话长度不能超过11位")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @Size(max = 32, message= "邮箱长度不能超过32位")
    private String email;

    @ApiModelProperty(value = "密码",required = true)
    @NotBlank(message = "密码不能为空")
    @Size(min = 6,max = 255, message= "密码长度为6-255位")
    private String password;

    @ApiModelProperty(value = "确认密码",required = true)
    @NotBlank(message = "确认密码不能为空")
    @Size(min = 6,max = 255, message= "密码长度为6-255位")
    private String verifyPassword;

    @ApiModelProperty(value = "渠道（1/2/3/4：WEB/ANDROID/IOS/H5）",required = true)
    @NotNull(message = "渠道不能为空")
    private Integer channel;
}
