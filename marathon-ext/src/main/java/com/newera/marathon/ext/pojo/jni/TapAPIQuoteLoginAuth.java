package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

@Data
public class TapAPIQuoteLoginAuth {

    //用户名
    private String userNo;

    //是否修改密码，'Y'表示是，'N'表示否
    private char modifyPassword;

    //用户密码
    private String password;

    //新密码（如果设置了修改密码则需要填写此字段）
    private String newPassword;

    //行情临时密码
    private String quoteTempPassword;

    //是否需要动态认证，'Y'表示是，'N'表示否
    private char dda;

    //动态认证序列号
    private String ddaSerialNo;
}
