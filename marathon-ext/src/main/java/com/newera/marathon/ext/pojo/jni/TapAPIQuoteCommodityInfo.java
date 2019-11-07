package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

@Data
public class TapAPIQuoteCommodityInfo {
    //品种
    private TapAPICommodity commodity;

    //品种名称,GBK编码格式
    private String commodityName;

    //品种英文名称
    private String commodityEngName;

    //每手乘数
    private double contractSize;

    //最小变动价位
    private double commodityTickSize;

    //报价分母
    private int commodityDenominator;

    //组合方向
    private char cmbDirect;

    //品种合约年限
    private int commodityContractLen;

    //是否夏令时,'Y'为是,'N'为否
    private char dst;

    //关联品种1
    private TapAPICommodity commodity1;

    //关联品种2
    private TapAPICommodity commodity2;

}
