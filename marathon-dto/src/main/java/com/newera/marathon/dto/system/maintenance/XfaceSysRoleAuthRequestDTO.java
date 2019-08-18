package com.newera.marathon.dto.system.maintenance;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class XfaceSysRoleAuthRequestDTO {


    @NotNull(message = "角色ID不能为空")
    private Integer roleId;

    private Integer resourceId;

}
