package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

/**
 * 客户账户手续费计算参数查询请求实体
 */
@Data
public class TapAPIAccountFeeRentQryReq {

    //客户资金账号
    private String accountNo;
}
