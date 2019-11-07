package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

import java.util.Date;

@Data
public class TapAPIQuoteContractInfo {

    //合约
    private TapAPIContract contract;

    //合约类型：'1'表示交易行情合约,'2'表示行情合约
    private char contractType;

    //行情真实合约
    private String quoteUnderlyingContract;

    //合约名称
    private String contractName;

    //合约到期日
    private Date contractExpDate;

    //最后交易日
    private Date lastTradeDate;

    //首次通知日
    private Date firstNoticeDate;
}
