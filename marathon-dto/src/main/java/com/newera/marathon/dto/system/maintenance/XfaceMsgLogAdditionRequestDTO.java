package com.newera.marathon.dto.system.maintenance;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class XfaceMsgLogAdditionRequestDTO {


    @NotBlank(message = "消息唯一标识不能为空")
    @Size(max = 32, message= "消息唯一标识长度不能超过32位")
    private String msgId;

    @Size(max = 1000, message= "消息体长度不能超过1000位")
    private String msgBody;

    @Size(max = 255, message= "交换机名称长度不能超过255位")
    private String exchange;

    @Size(max = 255, message= "路由键名称长度不能超过255位")
    private String routingKey;
}
