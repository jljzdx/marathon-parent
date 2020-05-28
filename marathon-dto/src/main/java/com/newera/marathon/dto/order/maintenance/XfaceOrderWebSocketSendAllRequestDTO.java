package com.newera.marathon.dto.order.maintenance;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class XfaceOrderWebSocketSendAllRequestDTO {


    @ApiModelProperty(value = "群发消息内容",required = true)
    @NotBlank(message = "群发消息内容不能为空")
    @Size(max = 255, message= "群发消息内容长度不能超过255位")
    private String message;

}
