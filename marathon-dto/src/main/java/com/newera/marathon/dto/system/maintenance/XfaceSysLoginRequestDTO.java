package com.newera.marathon.dto.system.maintenance;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class XfaceSysLoginRequestDTO {


    @ApiModelProperty(value = "用户名",required = true)
    @NotBlank(message = "用户名不能为空")
    @Size(max = 255, message= "用户名长度不能超过255位")
    private String userName;

    @ApiModelProperty(value = "密码",required = true)
    @NotBlank(message = "密码不能为空")
    @Size(max = 16, message= "密码长度不能超过16位")
    private String password;

    @ApiModelProperty(value = "图形验证码",required = true)
    @NotBlank(message = "图形验证码不能为空")
    @Size(max = 6, message= "图形验证码长度不能超过6位")
    private String captchaCode;

    @ApiModelProperty(value = "图形验证码ID",required = true)
    @NotBlank(message = "图形验证码ID不能为空")
    private String captchaId;

}
