package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

/**
 * 客户账户保证金计算参数查询应答
 */
@Data
public class TapAPIAccountMarginRentQryRsp {

    //资金账号
    private String accountNo;
    //交易所编号
    private String exchangeNo;
    //品种类型：TAPICommodityType
    private char commodityType;
    //品种编码
    private String commodityNo;
    //合同编码
    private String contractNo;
    //执行价
    private String strikePrice;
    //看涨看跌标示：TAPICallOrPutFlagType
    private char callOrPutFlag;
    //计算方式：TAPICalculateModeType
    private char calculateMode;
    //币种组号
    private String currencyGroupNo;
    //币种编号
    private String currencyNo;
    //保证金
    private double initialMargin;
    //维持保证金
    private double maintenanceMargin;
    //
    private double sellInitialMargin;
    //
    private double sellMaintenanceMargin;
    //
    private double lockMargin;
}
