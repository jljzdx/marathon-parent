package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

/**
 * 客户撤单请求实体
 * 必填项是ServerFlag和OrderNo
 */
@Data
public class TapAPIOrderCancelReq {


    //整型参考值
    private int refInt;

    //浮点参考值
    private double refDouble;

    //字符串参考值
    private String refString;

    //服务器标识
    private char serverFlag;

    //委托编码
    private String orderNo;
}
