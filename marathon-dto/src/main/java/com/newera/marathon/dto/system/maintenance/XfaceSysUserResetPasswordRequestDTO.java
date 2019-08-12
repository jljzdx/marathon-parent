package com.newera.marathon.dto.system.maintenance;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class XfaceSysUserResetPasswordRequestDTO {


    @NotNull(message = "用户ID不能为空")
    private Integer id;
}
