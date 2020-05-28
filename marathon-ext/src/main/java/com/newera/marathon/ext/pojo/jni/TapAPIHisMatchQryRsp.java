package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

import java.util.Date;

/**
 * 历史成交查询应答
 */
@Data
public class TapAPIHisMatchQryRsp {


    //结算日期
    private Date settleDate;
    //交易日期
    private Date tradeDate;
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
    //成交来源：TAPIMatchSourceType
    private char matchSource;
    //买卖方向：TAPISideType
    private char matchSide;
    //开平标志：TAPIPositionEffectType
    private char positionEffect;
    //投机保值：TAPIHedgeFlagType
    private char hedgeFlag;
    //成交价
    private double matchPrice;
    //成交量
    private int matchQty;
    //委托号
    private String orderNo;
    //成交序号
    private String matchNo;
    //成交流水号
    private int matchStreamID;
    //上手号
    private String upperNo;
    //组合号
    private String matchCmbNo;
    //成交编号(交易所成交号)
    private String exchangeMatchNo;
    //上手流水号
    private int matchUpperStreamID;
    //品种币种组
    private String commodityCurrencyGroup;
    //品种币种
    private String commodityCurrency;
    //成交金额
    private double turnover;
    //权利金收入
    private double premiumIncome;
    //权利金支出
    private double premiumPay;
    //客户手续费
    private double accountFee;
    //客户手续费币种组
    private String accountFeeCurrencyGroup;
    //客户手续费币种
    private String accountFeeCurrency;
    //人工客户手续费标记：TAPIYNFLAG
    private char isManualFee;
    //客户其他费用
    private double accountOtherFee;
    //上手手续费
    private double upperFee;
    //上手手续费
    private String upperFeeCurrencyGroup;
    //上手手续费币种
    private String upperFeeCurrency;
    //人工上手手续费标记：TAPIYNFLAG
    private char isUpperManualFee;
    //上手其他费用
    private double upperOtherFee;
    //成交时间
    private Date matchDateTime;
    //上手成交时间
    private Date upperMatchDateTime;
    //平仓盈亏
    private double closeProfit;
    //指定平仓价格
    private double closePrice;
    //平仓量
    private int closeQty;
    //结算分组
    private String settleGroupNo;
    //操作员
    private String operatorNo;
    //操作时间
    private Date operateTime;
}
