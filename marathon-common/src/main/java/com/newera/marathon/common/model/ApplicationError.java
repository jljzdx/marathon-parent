package com.newera.marathon.common.model;

public enum ApplicationError {
    ADDITION_FAILED("添加失败", "100001"),
    MODIFY_FAILED("修改失败", "100002"),
    DELETE_FAILED("删除失败", "100003"),
    //---------cos
    SMS_OVER_MAX_COUNT("操作过于频繁，请明天再试", "200001"),
    SMS_CODE_EXPIRED("验证码已过期，请重新获取验证码", "200002"),
    SMS_CODE_ERROR("验证码错误，请输入正确的验证码", "200003"),
    MAIL_SEND_FAILED("邮件发送失败", "200004"),
    //---------cms
    USER_NAME_ALREADY_EXIST("用户名已经存在", "300001"),
    ROLE_NAME_ALREADY_EXIST("角色名称已经存在", "300002"),
    USER_NAME_OR_PASSWORD_ERROR("用户名或密码有误", "300003"),
    CAPTCHA_ERROR("图形验证码有误", "300004"),
    OLD_PASSWORD_ERROR("原密码有误", "300005"),
    TWO_PASSWORD_ERROR("两次密码输入不一致", "300006"),
    USERID_STATUS_INVALID("无效的用户", "300007"),
    NOT_EXIST_PARENTID("父节点不存在", "300008"),
    NAME_SAME("同级资源名称不能重复", "300009"),
    //---------cust
    USER_NAME_EXISTED("用户名已存在", "400001"),
    PHONE_EXISTED("电话已存在", "400002"),
    PASSWWORD_UNSAME("两次密码不一致", "400003"),
    LOGIN_ACCOUNT_UNEXIST("登陆账号不存在", "400004"),
    LOGIN_PASSWWORD_ERROR("登陆密码不正确", "400005"),
    //---------web
    LOGIN_USER_ERROR("获取不到用户信息", "500001"),
    NON_AUTHORITATIVE_INFORMATION("未授权，请联系管理员", "500002"),
    ;
    private String message;
    private String code;

    ApplicationError(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
