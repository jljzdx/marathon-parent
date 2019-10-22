package com.newera.marathon.dto.cos.inquiry;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class XfaceCosMsgLogListInquiryRequestDTO {

    @Size(max = 32, message= "消息唯一标识长度不能超过32位")
    private String msgId;
    /**
     * 1/2/3/4：投递中/投递成功/投递失败/已消费
     */
    private Integer status;
}
