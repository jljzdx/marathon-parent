package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

import java.util.Date;

/**
 * 资金查询请求
 */
@Data
public class TapAPINewOrder {

    //客户资金帐号，必填
    private String accountNo;
    //交易所编号，必填
    private String exchangeNo;
    //品种类型，必填：TAPICommodityType
    private char commodityType;
    //品种编码类型，必填
    private String commodityNo;
    //合约1，必填
    private String contractNo;
    //执行价格1，期权填写
    private String strikePrice;
    //看张看跌1 默认N：TAPICallOrPutFlagType
    private char callOrPutFlag;
    //合约2，默认空
    private String contractNo2;
    //执行价格2，默认空
    private String strikePrice2;
    //看张看跌2 默认N：TAPICallOrPutFlagType
    private char callOrPutFlag2;
    //委托类型 必填：TAPIOrderTypeType
    private char orderType;
    //委托有效类型,默认当日有效：TAPIOrderSourceType
    private char orderSource;
    //委托有效类型,默认当日有效：TAPITimeInForceType
    private char timeInForce;
    //有效日期(GTD情况下使用)
    private Date expireTime;
    //是否风险报单，默认非风险保单：TAPIYNFLAG
    private char isRiskOrder;
    //买入卖出：TAPISideType
    private char orderSide;
    //开平标志1,默认N：TAPIPositionEffectType
    private char positionEffect;
    //开平标志2，默认N：TAPIPositionEffectType
    private char positionEffect2;
    //询价号
    private String inquiryNo;
    //投机保值，默认N：TAPIHedgeFlagType
    private char hedgeFlag;
    //委托价格1
    private double orderPrice;
    //委托价格2，做市商应价使用
    private double orderPrice2;
    //触发价格
    private double stopPrice;
    //委托数量，必填
    private int orderQty;
    //最小成交量，默认1
    private int orderMinQty;
    //冰山单最小随机量
    private int minClipSize;
    //冰山单最大随机量
    private int maxClipSize;
    //整型参考值
    private int refInt;
    //浮点参考值
    private double refDouble;
    //字符串参考值
    private String refString;
    //客户子账号，如果存在子账号，则自行上报子账号
    private String clientID;
    //策略单类型，默认N：TAPITacticsTypeType
    private char tacticsType;
    //触发条件，默认N：TAPITriggerConditionType
    private char triggerCondition;
    //触发价格类型，默认N：TAPITriggerPriceTypeType
    private char triggerPriceType;
    //是否T+1有效,默认T+1有效
    private char addOneIsValid;
}
