package com.newera.marathon.dto.cms.maintenance;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class XfaceCmsAdminRoleModifyRequestDTO {


    @NotNull(message = "角色ID不能为空")
    private Integer id;

    @Size(max = 50, message= "角色名称长度不能超过50位")
    private String roleName;

    @Size(max = 500, message= "描述长度不能超过500位")
    private String remark;

    @NotBlank(message = "更新人不能为空")
    @Size(max = 255, message= "更新人长度不能超过255位")
    private String modifyOperator;
}
