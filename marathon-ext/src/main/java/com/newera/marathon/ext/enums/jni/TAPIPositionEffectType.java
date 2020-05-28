package com.newera.marathon.ext.enums.jni;

/**
 * 开平类型
 */
public enum TAPIPositionEffectType {

    TAPI_PositionEffect_NONE('N',"不分开平"),
    TAPI_PositionEffect_OPEN('O',"开仓"),
    TAPI_PositionEffect_COVER('C',"平仓"),
    TAPI_PositionEffect_COVER_TODAY('T',"平当日"),
    ;

    private char code;

    private String name;

    TAPIPositionEffectType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
