package com.newera.marathon.ext.enums.jni;

/**
 * 帐单文件类型
 */
public enum TAPIBillFileTypeType {

    TAPI_BILL_FILE_TXT('T',"txt格式文件"),
    TAPI_BILL_FILE_PDF('F',"pdf格式文件"),
    ;

    private char code;

    private String name;

    TAPIBillFileTypeType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
