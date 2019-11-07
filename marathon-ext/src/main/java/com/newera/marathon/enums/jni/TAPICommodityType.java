package com.newera.marathon.enums.jni;

public enum TAPICommodityType {

    TAPI_COMMODITY_TYPE_NONE('N',"无"),
    TAPI_COMMODITY_TYPE_SPOT('P',"现货"),
    TAPI_COMMODITY_TYPE_FUTURES('F',"期货"),
    TAPI_COMMODITY_TYPE_OPTION('O',"期权"),
    TAPI_COMMODITY_TYPE_SPREAD_MONTH('S',"跨期套利"),
    TAPI_COMMODITY_TYPE_SPREAD_COMMODITY('M',"跨品种套利"),
    TAPI_COMMODITY_TYPE_BUL('U',"看涨垂直套利"),
    TAPI_COMMODITY_TYPE_BER('E',"看跌垂直套利"),
    TAPI_COMMODITY_TYPE_STD('D',"跨式套利"),
    TAPI_COMMODITY_TYPE_STG('G',"宽跨式套利"),
    TAPI_COMMODITY_TYPE_PRT('R',"备兑组合"),
    TAPI_COMMODITY_TYPE_BLT('L',"看涨水平期权"),
    TAPI_COMMODITY_TYPE_BRT('Q',"看跌水平期权"),
    TAPI_COMMODITY_TYPE_DIRECTFOREX('X',"外汇——直接汇率"),
    TAPI_COMMODITY_TYPE_INDIRECTFOREX('I',"外汇——间接汇率"),
    TAPI_COMMODITY_TYPE_CROSSFOREX('C',"外汇——交叉汇率"),
    TAPI_COMMODITY_TYPE_INDEX('Z',"指数"),
    TAPI_COMMODITY_TYPE_STOCK('T',"股票"),
    TAPI_COMMODITY_TYPE_SPOT_TRADINGDEFER('Y',"现货延期"),
    TAPI_COMMODITY_TYPE_FUTURE_LOCK('J',"中金所对锁组合"),
    TAPI_COMMODITY_TYPE_EFP('A',"中金所EFP"),
    ;

    private char code;

    private String name;

    TAPICommodityType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
