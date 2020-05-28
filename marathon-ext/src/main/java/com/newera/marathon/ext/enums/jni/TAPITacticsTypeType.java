package com.newera.marathon.ext.enums.jni;

public enum TAPITacticsTypeType {

    TAPI_TACTICS_TYPE_NONE('N',"无"),
    TAPI_TACTICS_TYPE_READY('M',"预备单(埋单)"),
    TAPI_TACTICS_TYPE_ATUO('A',"自动单"),
    TAPI_TACTICS_TYPE_CONDITION('C',"条件单"),
    ;

    private char code;

    private String name;

    TAPITacticsTypeType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
