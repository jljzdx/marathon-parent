package com.newera.marathon.dto.system.maintenance;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class XfaceMsgLogModifyRequestDTO {

    @NotBlank(message = "消息唯一标识不能为空")
    @Size(max = 32, message= "消息唯一标识长度不能超过32位")
    private String msgId;

    private Integer tryCount;

    private Integer status;
}
