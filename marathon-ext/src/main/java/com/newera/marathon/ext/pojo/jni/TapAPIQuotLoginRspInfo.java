package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

import java.util.Date;

@Data
public class TapAPIQuotLoginRspInfo {

    //用户名
    private String userNo;

    //用户类型
    private int userType;

    //昵称，GBK编码格式
    private String userName;

    //行情临时密码
    private String quoteTempPassword;

    //用户自己设置的预留信息
    private String reservedInfo;

    //上次登录的地址
    private String lastLoginIP;

    //上次登录使用的端口
    private int lastLoginProt;

    //上次登录的时间
    private Date lastLoginTime;

    //上次退出的时间
    private Date lastLogoutTime;

    //当前交易日期
    private Date tradeDate;

    //上次结算时间
    private Date lastSettleTime;

    //系统启动时间
    private Date startTime;

    //系统初始化时间
    private Date initTime;
}
