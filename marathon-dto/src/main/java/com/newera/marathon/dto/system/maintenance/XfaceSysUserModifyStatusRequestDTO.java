package com.newera.marathon.dto.system.maintenance;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class XfaceSysUserModifyStatusRequestDTO {


    @NotNull(message = "用户ID不能为空")
    private Integer id;

    @NotNull(message = "用户状态不能为空")
    private Integer status;

}
