package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

/**
 * 委托流程查询
 */
@Data
public class TapAPIOrderProcessQryReq {

    //服务器标识
    private char serverFlag;
    //委托编号
    private String orderNo;
}
