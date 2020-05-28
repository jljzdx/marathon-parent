package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

/**
 * 客户改单请求
 * 必填项有ServerFlag，OrderNo,以及委托价和委托量，止损价。其他字段咱们没有用。
 */
@Data
public class TapAPIAmendOrder {

    //报单请求数据
    private TapAPINewOrder reqData;
    //服务器标识
    private char serverFlag;
    //委托编号
    private String orderNo;
}
