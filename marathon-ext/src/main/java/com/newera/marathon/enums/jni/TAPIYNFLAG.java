package com.newera.marathon.enums.jni;

public enum TAPIYNFLAG {

    APIYNFLAG_YES('Y',"是"),
    APIYNFLAG_NO('N',"否"),
    ;

    private char code;

    private String name;

    TAPIYNFLAG(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
