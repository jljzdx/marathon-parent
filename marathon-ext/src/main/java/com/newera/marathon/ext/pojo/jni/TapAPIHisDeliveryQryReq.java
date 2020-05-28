package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

import java.util.Date;

/**
 * 交割查询请求实体
 */
@Data
public class TapAPIHisDeliveryQryReq {

    //客户资金账号
    private String accountNo;
    //客户属性号
    private String accountAttributeNo;
    //必填
    private Date beginDate;
    //必填
    private Date endDate;
    //统计类型
    private char countType;
}
