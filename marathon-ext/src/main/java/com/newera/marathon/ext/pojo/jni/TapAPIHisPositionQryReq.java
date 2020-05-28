package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

import java.util.Date;

/**
 * 历史持仓查询请求实体
 */
@Data
public class TapAPIHisPositionQryReq {

    //客户资金账号
    private String accountNo;
    //客户属性号
    private String accountAttributeNo;
    //日期
    private Date date;
    //统计类型
    private char countType;
}
