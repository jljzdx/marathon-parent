package com.newera.marathon.service.cust.model;

public enum ApplicationError {
    ADDITION_FAILED("添加失败", "300001"),
    MODIFY_FAILED("修改失败", "300002"),
    DELETE_FAILED("删除失败", "300003"),
    USER_NAME_EXISTED("用户名已存在", "300004"),
    PHONE_EXISTED("电话已存在", "300005"),
    PASSWWORD_UNSAME("两次密码不一致", "300006"),
    LOGIN_ACCOUNT_UNEXIST("登陆账号不存在", "300007"),
    LOGIN_PASSWWORD_ERROR("登陆密码不正确", "300008"),
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
