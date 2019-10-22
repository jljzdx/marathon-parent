package com.newera.marathon.dto.cms.maintenance;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class XfaceCmsAdminRoleAdditionRequestDTO {


    @NotBlank(message = "角色名称不能为空")
    @Size(max = 50, message= "角色名称长度不能超过50位")
    private String roleName;

    @Size(max = 500, message= "描述长度不能超过500位")
    private String remark;

    @NotBlank(message = "创建人不能为空")
    @Size(max = 255, message= "创建人长度不能超过255位")
    private String createOperator;
}
