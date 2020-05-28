package com.newera.marathon.ext.pojo.jni;

import lombok.Data;

/**
 * 修改密码请求
 */
@Data
public class TapAPIChangePasswordReq {

    //旧密码
    private String oldPassword;

    //新密码
    private String newPassword;
}
