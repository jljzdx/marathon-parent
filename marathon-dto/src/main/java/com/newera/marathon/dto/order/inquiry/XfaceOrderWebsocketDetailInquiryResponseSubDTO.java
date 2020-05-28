package com.newera.marathon.dto.order.inquiry;

import lombok.Data;

@Data
public class XfaceOrderWebsocketDetailInquiryResponseSubDTO {

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 客户端地址
     */
    private String address;

    /**
     * 连接时间
     */
    private String createTime;
}
