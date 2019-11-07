package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

@Data
public class TapAPIContract {

    //交易所编码
    private TapAPICommodity commodity;

    //合约代码1
    private String contractNo1;

    //执行价1
    private String strikePrice1;

    //看涨看跌标示1
    private char callOrPutFlag1;

    //合约代码2
    private String contractNo2;

    //执行价2
    private String strikePrice2;

    //看涨看跌标示2
    private String callOrPutFlag2;
}
