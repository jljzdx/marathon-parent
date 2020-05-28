package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

import java.util.Date;

/**
 * 客户资金调整查询请求实体
 */
@Data
public class TapAPIHisOrderQryReq {

    //登录用户名
    private String userNo;
    //客户资金账号
    private String accountNo;
    //客户属性号
    private String accountAttributeNo;
    //必填
    private Date beginDate;
    //必填
    private Date endDate;
}
