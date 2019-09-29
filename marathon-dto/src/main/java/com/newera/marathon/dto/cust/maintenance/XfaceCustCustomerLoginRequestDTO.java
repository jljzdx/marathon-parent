package com.newera.marathon.dto.cust.maintenance;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class XfaceCustCustomerLoginRequestDTO {

    @ApiModelProperty(value = "登陆账号（用户名/手机号）",required = true)
    @NotBlank(message = "账号不能为空")
    @Size(max = 32, message= "账号长度不能超过32位")
    private String loginAccount;

    @ApiModelProperty(value = "密码",required = true)
    @NotBlank(message = "密码不能为空")
    @Size(min = 6,max = 255, message= "密码长度为6-255位")
    private String password;
}
