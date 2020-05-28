package com.newera.marathon.ext.enums.jni;

/**
 * 委托类型
 */
public enum TAPIOrderTypeType {

    TAPI_ORDER_TYPE_MARKET('1',"市价"),
    TAPI_ORDER_TYPE_LIMIT('2',"限价"),
    TAPI_ORDER_TYPE_STOP_MARKET('3',"市价止损"),
    TAPI_ORDER_TYPE_STOP_LIMIT('4',"限价止损"),
    TAPI_ORDER_TYPE_OPT_EXEC('5',"期权行权"),
    TAPI_ORDER_TYPE_OPT_ABANDON('6',"期权弃权"),
    TAPI_ORDER_TYPE_REQQUOT('7',"询价"),
    TAPI_ORDER_TYPE_RSPQUOT('8',"应价"),
    TAPI_ORDER_TYPE_ICEBERG('9',"冰山单"),
    TAPI_ORDER_TYPE_GHOST('A',"影子单"),
    ;

    private char code;

    private String name;

    TAPIOrderTypeType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
