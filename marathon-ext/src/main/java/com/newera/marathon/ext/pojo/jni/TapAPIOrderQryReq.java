package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

import java.util.Date;

/**
 * 委托查询请求实体
 */
@Data
public class TapAPIOrderQryReq {

    //资金账号
    private String accountNo;
    //交易所编号
    private String exchangeNo;
    //品种类型：TAPICommodityType
    private char commodityType;
    //品种编码
    private String commodityNo;
    //委托类型：TAPIOrderTypeType
    private char orderType;
    //委托来源：TAPIOrderSourceType
    private char orderSource;
    //委托有效类型：TAPITimeInForceType
    private char timeInForce;
    //有效日期(GTD情况下使用)
    private Date expireTime;
    //是否风险报单：TAPIYNFLAG
    private char isRiskOrder;
    //服务器标识
    private char serverFlag;
    //委托编号
    private String orderNo;
    //是否为录入委托单：TAPIYNFLAG
    private char isBackInput;
    //委托成交删除标：TAPIYNFLAG
    private char isDeleted;
    //是否为T+1单：TAPIYNFLAG
    private char isAddOne;
}
