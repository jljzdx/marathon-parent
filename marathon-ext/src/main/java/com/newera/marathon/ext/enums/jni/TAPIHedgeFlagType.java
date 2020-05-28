package com.newera.marathon.ext.enums.jni;

public enum TAPIHedgeFlagType {

    TAPI_HEDGEFLAG_NONE('N',"无"),
    TAPI_HEDGEFLAG_T('T',"投机"),
    TAPI_HEDGEFLAG_B('B',"保值"),
    TAPI_HEDGEFLAG_L('L',"套利"),
    ;

    private char code;

    private String name;

    TAPIHedgeFlagType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
