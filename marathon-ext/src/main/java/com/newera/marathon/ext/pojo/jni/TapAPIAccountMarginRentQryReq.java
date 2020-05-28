package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

/**
 * 客户账户保证金计算参数查询请求实体
 */
@Data
public class TapAPIAccountMarginRentQryReq {

    //资金账号
    private String accountNo;
    //交易所编号
    private String exchangeNo;
    //品种类型：TAPICommodityType
    private char commodityType;
    //品种编码
    private String commodityNo;
}
