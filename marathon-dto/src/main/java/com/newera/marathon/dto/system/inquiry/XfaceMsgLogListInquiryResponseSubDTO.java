package com.newera.marathon.dto.system.inquiry;

import lombok.Data;

@Data
public class XfaceMsgLogListInquiryResponseSubDTO {

    private String msgId;

    private String msgBody;

    private String exchange;

    private String routingKey;

    private Integer tryCount;

    private Integer status;
}
