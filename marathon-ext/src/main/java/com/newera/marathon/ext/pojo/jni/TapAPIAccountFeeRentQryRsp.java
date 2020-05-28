package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

/**
 * 客户账户手续费计算参数查询应答
 */
@Data
public class TapAPIAccountFeeRentQryRsp {

    //客户资金账号
    private String accountNo;
    //交易所编号
    private String exchangeNo;
    //TAPICommodityType
    private char commodityType;
    //品种编码
    private String commodityNo;
    //TAPIMatchSourceType
    private char matchSource;
    //TAPICalculateModeType
    private char calculateMode;
    //币种组号
    private String currencyGroupNo;
    //币种编号
    private String currencyNo;
    //
    private double openCloseFee;
    //
    private double closeTodayFee;
}
