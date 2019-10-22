package com.newera.marathon.dto.cms.maintenance;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class XfaceCmsAdminUserModifyPasswordRequestDTO {


    @NotNull(message = "用户ID不能为空")
    private Integer id;

    @NotBlank(message = "原密码不能为空")
    @Size(max = 32, message= "原密码长度不能超过16位")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @Size(max = 32, message= "新密码长度不能超过16位")
    private String newPassword;

    @NotBlank(message = "确认新密码不能为空")
    @Size(max = 16, message= "确认新密码长度不能超过16位")
    private String repeatNewPassword;

    @NotBlank(message = "更新人不能为空")
    @Size(max = 255, message= "更新人长度不能超过255位")
    private String modifyOperator;
}
