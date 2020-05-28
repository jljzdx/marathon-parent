package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

import java.util.Date;

/**
 * 交割查询应答
 */
@Data
public class TapAPIHisDeliveryQryRsp {

    //交割日期
    private Date deliveryDate;
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
    //成交来源：TAPIMatchSourceType
    private char matchSource;
    //开仓方向：TAPISideType
    private char openSide;
    //开仓价格
    private double openPrice;
    //交割价格
    private double deliveryPrice;
    //交割量
    private int deliveryQty;
    //冻结量
    private int frozenQty;
    //开仓成交号
    private String openNo;
    //上手编号
    private String upperNo;
    //品种币种
    private String commodityCurrencyGroup;
    //品种币种
    private String commodityCurrency;
    //上日结算价
    private double preSettlePrice;
    //交割盈亏
    private double deliveryProfit;
    //客户初始冻结保证金
    private double accountFrozenInitialMargin;
    //客户维持冻结保证金
    private double accountFrozenMaintenanceMargin;
    //上手初始冻结保证金
    private double upperFrozenInitialMargin;
    //上手维持冻结保证金
    private double upperFrozenMaintenanceMargin;
    //客户手续费币种组
    private String accountFeeCurrencyGroup;
    //客户手续费币种
    private String accountFeeCurrency;
    //客户交割手续费
    private double accountDeliveryFee;
    //上手手续费币种组
    private String upperFeeCurrencyGroup;
    //上手手续费币种
    private String upperFeeCurrency;
    //上手交割手续费
    private double upperDeliveryFee;
    //交割行权方式：TAPIDeliveryModeType
    private char deliveryMode;
    //操作员
    private String operatorNo;
    //操作时间
    private Date operateTime;
    //结算分组
    private String settleGroupNo;
}
