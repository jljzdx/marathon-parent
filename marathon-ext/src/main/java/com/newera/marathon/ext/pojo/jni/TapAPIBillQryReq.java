package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

import java.util.Date;

/**
 * 客户账单查询请求实体
 */
@Data
public class TapAPIBillQryReq {

    private String userNo;
    //账单类型：TAPIBillTypeType
    private char billType;

    private Date billDate;
    //帐单文件类型：TAPIBillFileTypeType
    private char billFileType;
}
