package com.newera.marathon.ext.enums.jni;

/**
 * 交割行权方式,期货和期权了结的方式
 */
public enum TAPIDeliveryModeType {

    TAPI_DELIVERY_MODE_GOODS('G',"实物交割"),
    TAPI_DELIVERY_MODE_CASH('C',"现金交割"),
    TAPI_DELIVERY_MODE_EXECUTE('E',"期权行权"),
    TAPI_DELIVERY_MODE_ABANDON('A',"期权放弃"),
    TAPI_DELIVERY_MODE_HKF('H',"港交所行权"),
    ;

    private char code;

    private String name;

    TAPIDeliveryModeType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
