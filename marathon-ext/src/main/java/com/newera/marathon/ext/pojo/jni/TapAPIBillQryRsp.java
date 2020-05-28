package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

/**
 * 客户账单查询应答
 */
@Data
public class TapAPIBillQryRsp {

    private TapAPIBillQryReq reqdata;
    //账单内容
    private String billText;
}
