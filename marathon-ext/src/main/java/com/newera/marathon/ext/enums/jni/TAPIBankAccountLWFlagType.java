package com.newera.marathon.ext.enums.jni;

/**
 * 本外币标识
 */
public enum TAPIBankAccountLWFlagType {

    TAPI_LWFlag_L('L',"境内人民币账户"),
    TAPI_LWFlag_W('W',"客户境内外币账户"),
    ;

    private char code;

    private String name;

    TAPIBankAccountLWFlagType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
