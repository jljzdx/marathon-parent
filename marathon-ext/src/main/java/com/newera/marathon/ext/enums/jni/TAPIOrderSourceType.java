package com.newera.marathon.ext.enums.jni;

/**
 * 委托来源
 */
public enum TAPIOrderSourceType {

    TAPI_ORDER_SOURCE_SELF_ETRADER('1',"自助电子单"),
    TAPI_ORDER_SOURCE_PROXY_ETRADER('2',"代理电子单"),
    TAPI_ORDER_SOURCE_JTRADER('3',"外部电子单(外部电子系统下单，本系统录入)"),
    TAPI_ORDER_SOURCE_MANUAL('4',"人工录入单(外部其他方式下单，本系统录入)"),
    TAPI_ORDER_SOURCE_CARRY('5',"carry单"),
    TAPI_ORDER_SOURCE_PROGRAM('6',"程式化报单"),
    TAPI_ORDER_SOURCE_DELIVERY('7',"交割行权"),
    TAPI_ORDER_SOURCE_ABANDON('8',"期权放弃"),
    TAPI_ORDER_SOURCE_CHANNEL('9',"通道费"),
    TAPI_ORDER_SOURCE_ESUNNY_API('A',"易盛API"),
    ;

    private char code;

    private String name;

    TAPIOrderSourceType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
