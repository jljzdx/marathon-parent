package com.newera.marathon.ext.enums.jni;

public enum TAPITriggerConditionType {

    TAPI_TRIGGER_CONDITION_NONE('N',"无"),
    TAPI_TRIGGER_CONDITION_GREAT('G',"大于等于"),
    TAPI_TRIGGER_CONDITION_LITTLE('L',"小于等于"),
    ;

    private char code;

    private String name;

    TAPITriggerConditionType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
