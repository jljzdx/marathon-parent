package com.newera.marathon.service.cms.model;

public enum  ApplicationError {
    SYS_ADDITION_FAILED("添加失败", "100001"),
    SYS_MODIFY_FAILED("修改失败", "100002"),
    SYS_DELETE_FAILED("删除失败", "100003"),
    USER_NAME_ALREADY_EXIST("用户名已经存在", "100004"),
    ROLE_NAME_ALREADY_EXIST("角色名称已经存在", "100005"),
    USER_NAME_OR_PASSWORD_ERROR("用户名或密码有误", "100006"),
    CAPTCHA_ERROR("图形验证码有误", "100007"),
    OLD_PASSWORD_ERROR("原密码有误", "100008"),
    TWO_PASSWORD_ERROR("两次密码输入不一致", "100009"),
    USERID_STATUS_INVALID("无效的用户", "100010"),
    NOT_EXIST_PARENTID("父节点不存在", "100011"),
    NAME_SAME("同级资源名称不能重复", "100011")
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
