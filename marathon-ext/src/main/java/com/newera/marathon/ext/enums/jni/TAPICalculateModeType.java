package com.newera.marathon.ext.enums.jni;

/**
 * 计算方式
 */
public enum TAPICalculateModeType {

    TAPI_CALULATE_MODE_COMBINE('0',"比例+定额（仅限手续费）大于0.01部分为定额，小于0.01部分为比例"),//如：0.001为比例收取1%
    TAPI_CALCULATE_MODE_PERCENTAGE('1',"比例"),
    TAPI_CALCULATE_MODE_QUOTA('2',"定额"),
    TAPI_CALCULATE_MODE_CHAPERCENTAGE('3',"差值比例"),
    TAPI_CALCULATE_MODE_CHAQUOTA('4',"差值定额"),
    TAPI_CALCULATE_MODE_DISCOUNT('5',"折扣"),
    ;

    private char code;

    private String name;

    TAPICalculateModeType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
