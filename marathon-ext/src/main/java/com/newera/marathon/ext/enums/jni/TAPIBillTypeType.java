package com.newera.marathon.ext.enums.jni;

/**
 * 账单类型
 */
public enum TAPIBillTypeType {

    TAPI_BILL_DATE('D',"日账单"),
    TAPI_BILL_MONTH('M',"月账单"),
    ;

    private char code;

    private String name;

    TAPIBillTypeType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
