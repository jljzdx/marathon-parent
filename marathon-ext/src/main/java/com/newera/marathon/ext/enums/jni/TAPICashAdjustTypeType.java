package com.newera.marathon.ext.enums.jni;

/**
 * 资金调整类型
 */
public enum TAPICashAdjustTypeType {

    TAPI_CASHINOUT_MODE_FEEADJUST('0',"手续费调整"),
    TAPI_CASHINOUT_MODE_YKADJUST('1',"盈亏调整"),
    TAPI_CASHINOUT_MODE_PLEDGE('2',"质押资金"),
    TAPI_CASHINOUT_MODE_INTERESTREVENUE('3',"利息收入"),
    TAPI_CASHINOUT_MODE_COLLECTIONCOST('4',"代扣费用"),
    TAPI_CASHINOUT_MODE_OTHER('5',"其它"),
    TAPI_CASHINOUT_MODE_COMPANY('6',"公司间拨账"),
    ;

    private char code;

    private String name;

    TAPICashAdjustTypeType(char code, String name) {
        this.code = code;
        this.name = name;
    }
}
