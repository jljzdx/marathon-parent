package com.newera.marathon.dto.system.maintenance;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class XfaceSysRoleAdditionRequestDTO {


    @NotBlank(message = "角色名称不能为空")
    @Size(max = 50, message= "角色名称长度不能超过50位")
    private String roleName;

    @Size(max = 500, message= "描述长度不能超过500位")
    private String remark;
}
