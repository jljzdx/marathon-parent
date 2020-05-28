package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

import java.util.Date;

/**
 * 客户资金调整查询应答
 */
@Data
public class TapAPIAccountCashAdjustQryRsp {

    //日期
    private Date date;
    //客户资金账号
    private String accountNo;
    //资金调整类型：TAPICashAdjustTypeType
    private char cashAdjustType;
    //币种组号
    private String currencyGroupNo;
    //币种编号
    private String currencyNo;
    //资金调整金额
    private double cashAdjustValue;
    //资金调整备注
    private String cashAdjustRemark;
    //操作时间
    private Date operateTime;
    //操作员
    private String operatorNo;
    //客户银行
    private String accountBank;
    //客户银行账号
    private String bankAccount;
    //客户本外币标识：TAPIBankAccountLWFlagType
    private char accountLWFlag;
    //公司银行
    private String companyBank;
    //公司银行账户
    private String internalBankAccount;
    //公司本外币标识：TAPIBankAccountLWFlagType
    private char companyLWFlag;

}
