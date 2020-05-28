package com.newera.marathon.ext.enums.jni;

/**
 * 看涨看跌标示
 */
public enum TAPICallOrPutFlagType {

    TAPI_CALLPUT_FLAG_CALL('C',"买权"),
    TAPI_CALLPUT_FLAG_PUT('P',"卖权"),
    TAPI_CALLPUT_FLAG_NONE('N',"无"),
    ;

    private char code;

    private String name;

    TAPICallOrPutFlagType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
