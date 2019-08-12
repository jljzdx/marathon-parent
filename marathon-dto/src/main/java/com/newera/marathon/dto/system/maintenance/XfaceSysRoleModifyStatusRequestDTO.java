package com.newera.marathon.dto.system.maintenance;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class XfaceSysRoleModifyStatusRequestDTO {


    @NotNull(message = "角色ID不能为空")
    private Integer id;

    @NotNull(message = "角色状态不能为空")
    private Integer status;

}
