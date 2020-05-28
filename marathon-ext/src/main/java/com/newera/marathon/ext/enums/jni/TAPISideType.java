package com.newera.marathon.ext.enums.jni;

/**
 * 买卖类型
 */
public enum TAPISideType {

    TAPI_SIDE_NONE('N',"无"),
    TAPI_SIDE_BUY('B',"买入"),
    TAPI_SIDE_SELL('S',"卖出"),
    ;

    private char code;

    private String name;

    TAPISideType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
