package com.newera.marathon.ext.enums.jni;

/**
 * 委托有效类型
 */
public enum TAPITimeInForceType {

    TAPI_ORDER_TIMEINFORCE_GFD('0',"当日有效"),
    TAPI_ORDER_TIMEINFORCE_GTC('1',"取消前有效"),
    TAPI_ORDER_TIMEINFORCE_GTD('2',"指定日期前有效"),
    TAPI_ORDER_TIMEINFORCE_FAK('3',"FAK或IOC"),
    TAPI_ORDER_TIMEINFORCE_FOK('4',"FOK"),
    ;

    private char code;

    private String name;

    TAPITimeInForceType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
