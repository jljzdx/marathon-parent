package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

import java.util.Date;

/**
 * 历史委托流程查询请求实体
 */
@Data
public class TapAPIHisOrderProcessQryReq {

    private Date date;
    //委托编号
    private String orderNo;
}
