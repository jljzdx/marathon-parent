package com.newera.marathon.ext.enums.jni;

public enum TAPITriggerPriceTypeType {

    TAPI_TRIGGER_PRICE_NONE('N',"无"),
    TAPI_TRIGGER_PRICE_BUY('B',"买价"),
    TAPI_TRIGGER_PRICE_SELL('S',"卖价"),
    TAPI_TRIGGER_PRICE_LAST('L',"最新价"),
    ;

    private char code;

    private String name;

    TAPITriggerPriceTypeType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
