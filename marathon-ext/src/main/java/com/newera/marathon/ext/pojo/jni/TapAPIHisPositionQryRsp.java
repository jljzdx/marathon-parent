package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

import java.util.Date;

/**
 * 交割查询应答
 */
@Data
public class TapAPIHisPositionQryRsp {

    //结算日期
    private Date settleDate;
    //开仓日期
    private Date openDate;
    //客户资金账号
    private String accountNo;
    //市场号或交易所代码
    private String exchangeNo;
    //品种类型：TAPICommodityType
    private char commodityType;
    //品种编码
    private String commodityNo;
    //合约编码
    private String contractNo;
    //执行价
    private String strikePrice;
    //看涨看跌标志：TAPICallOrPutFlagType
    private char callOrPutFlag;
    //买卖方向：TAPISideType
    private char matchSide;
    //投机保值：TAPIHedgeFlagType
    private char hedgeFlag;
    //持仓价格
    private double positionPrice;
    //持仓量
    private int positionQty;
    //订单号
    private String orderNo;
    //持仓编号
    private String positionNo;
    //上手号
    private String upperNo;
    //品种币种组
    private String currencyGroup;
    //品种币种
    private String currency;
    //上日结算价格
    private double preSettlePrice;
    //结算价格
    private double settlePrice;
    //持仓盈亏(盯市)
    private double positionDProfit;
    //LME持仓盈亏
    private double lMEPositionProfit;
    //期权市值
    private double optionMarketValue;
    //客户初始保证金
    private double accountInitialMargin;
    //客户维持保证金
    private double accountMaintenanceMargin;
    //上手初始保证金
    private double upperInitialMargin;
    //上手维持保证金
    private double UpperMaintenanceMargin;
    //结算分组
    private String settleGroupNo;
}
