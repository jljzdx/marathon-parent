package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

/**
 * 品种编码实体
 */
@Data
public class TapAPICommodity {
    //交易所编码
    private String exchangeNo;

    //品种编号
    private String commodityNo;

    //品种类型：TAPICommodityType
    private char commodityType;
}
