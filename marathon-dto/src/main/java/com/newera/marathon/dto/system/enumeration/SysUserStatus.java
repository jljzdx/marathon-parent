package com.newera.marathon.dto.system.enumeration;

public enum SysUserStatus {
    _0(0,"无效"),
    _1(1,"有效"),
    ;

    private Integer code;

    private String text;

    SysUserStatus(Integer code, String text) {
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

    public static SysUserStatus getEnums(String code) {
        for (SysUserStatus enums : values()) {
            if (code.equals(enums.getCode())) {
                return enums;
            }
        }
        return null;
    }
}
