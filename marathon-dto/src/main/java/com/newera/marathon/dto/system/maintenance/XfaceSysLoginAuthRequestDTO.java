package com.newera.marathon.dto.system.maintenance;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class XfaceSysLoginAuthRequestDTO {


    @NotBlank(message = "用户名不能为空")
    @Size(max = 75, message= "用户名长度不能超过75位")
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Size(max = 16, message= "密码长度不能超过16位")
    private String password;

    @NotBlank(message = "图形验证码不能为空")
    @Size(max = 6, message= "图形验证码长度不能超过6位")
    private String captchaCode;

    @NotBlank(message = "图形验证码ID不能为空")
    private String captchaId;

}
