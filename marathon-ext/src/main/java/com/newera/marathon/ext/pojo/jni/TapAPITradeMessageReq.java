package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

import java.util.Date;

/**
 * 交易或风控消息查询请求实体
 */
@Data
public class TapAPITradeMessageReq {

    //客户资金账号
    private String accountNo;
    //客户属性号
    private String accountAttributeNo;

    private Date benginSendDateTime;

    private Date endSendDateTime;
}
