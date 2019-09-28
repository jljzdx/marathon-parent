package com.newera.marathon.service.cos.model;

public enum ApplicationError {
    ADDITION_FAILED("添加失败", "200001"),
    MODIFY_FAILED("修改失败", "200002"),
    DELETE_FAILED("删除失败", "200003"),
    SMS_OVER_MAX_COUNT("操作过于频繁，请明天再试", "200004"),
    SMS_CODE_EXPIRED("验证码已过期，请重新获取验证码", "200005"),
    SMS_CODE_ERROR("验证码错误，请输入正确的验证码", "200006"),
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
