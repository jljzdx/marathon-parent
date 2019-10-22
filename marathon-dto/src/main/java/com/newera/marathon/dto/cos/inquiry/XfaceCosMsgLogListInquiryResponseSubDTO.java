package com.newera.marathon.dto.cos.inquiry;

import lombok.Data;

@Data
public class XfaceCosMsgLogListInquiryResponseSubDTO {

    private String msgId;

    private String msgBody;

    private String exchange;

    private String routingKey;

    private Integer tryCount;

    private Integer status;
}
