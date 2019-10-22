package com.newera.marathon.dto.cms.enumeration;

public enum AdminUserStatus {
    _0(0,"无效"),
    _1(1,"有效"),
    ;

    private Integer code;

    private String text;

    AdminUserStatus(Integer code, String text) {
        this.code = code;
        this.text = text;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static AdminUserStatus getEnums(String code) {
        for (AdminUserStatus enums : values()) {
            if (code.equals(enums.getCode())) {
                return enums;
            }
        }
        return null;
    }
}
