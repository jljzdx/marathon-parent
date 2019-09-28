package com.newera.marathon.dto.cos.maintenance;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class XfaceCosSendSmsRequestDTO {


    @ApiModelProperty(value = "电话",required = true)
    @NotBlank(message = "电话不能为空")
    private String phone;

    @ApiModelProperty(value = "短信发送类型（0/1/2：普通发短信/注册/忘记密码）",required = true)
    @NotNull(message = "短信发送类型不能为空")
    private Integer type;

}
