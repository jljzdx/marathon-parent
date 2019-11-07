package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

import java.util.Date;

/**
 * 行情全文
 */
@Data
public class TapAPIQuoteWhole {

    //合约
    private TapAPIContract contract;
    //币种编号
    private String currencyNo;
    //交易状态。1,集合竞价;2,集合竞价撮合;3,连续交易;4,交易暂停;5,闭市
    private char tradingState;
    //时间戳
    private Date dateTimeStamp;
    //昨收盘价
    private double qPreClosingPrice;
    //昨结算价
    private double qPreSettlePrice;
    //昨持仓量
    private long qPrePositionQty;
    //开盘价
    private double qOpeningPrice;
    //最新价
    private double qLastPrice;
    //最高价
    private double qHighPrice;
    //最低价
    private double qLowPrice;
    //历史最高价
    private double qHisHighPrice;
    //历史最低价
    private double qHisLowPrice;
    //涨停价
    private double qLimitUpPrice;
    //跌停价
    private double qLimitDownPrice;
    //当日总成交量
    private long qTotalQty;
    //当日成交金额
    private double qTotalTurnover;
    //持仓量
    private long qPositionQty;
    //均价
    private double qAveragePrice;
    //收盘价
    private double qClosingPrice;
    //结算价
    private double qSettlePrice;
    //最新成交量
    private long qLastQty;
    //买价1-20档
    private double qBidPrice;
    //买量1-20档
    private long qBidQty;
    //卖价1-20档
    private double qAskPrice;
    //卖量1-20档
    private long qAskQty;
    //隐含买价
    private double qImpliedBidPrice;
    //隐含买量
    private long qImpliedBidQty;
    //隐含卖价
    private double qImpliedAskPrice;
    //隐含卖量
    private long qImpliedAskQty;
    //昨虚实度
    private double qPreDelta;
    //今虚实度
    private double qCurrDelta;
    //内盘量
    private long qInsideQty;
    //外盘量
    private long qOutsideQty;
    //换手率
    private double qTurnoverRate;
    //五日均量
    private long q5DAvgQty;
    //市盈率
    private double qPERatio;
    //总市值
    private double qTotalValue;
    //流通市值
    private double qNegotiableValue;
    //持仓走势
    private long qPositionTrend;
    //涨速
    private double qChangeSpeed;
    //涨幅
    private double qChangeRate;
    //涨跌值
    private double qChangeValue;
    //振幅
    private double qSwing;
    //委买总量
    private long qTotalBidQty;
    //委卖总量
    private long qTotalAskQty;
    //虚拟合约对应的真实合约
    private TapAPIContract underlyContract;
}
