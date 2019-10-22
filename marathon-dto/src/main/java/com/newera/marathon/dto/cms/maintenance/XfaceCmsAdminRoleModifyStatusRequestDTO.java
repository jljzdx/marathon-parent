package com.newera.marathon.dto.cms.maintenance;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class XfaceCmsAdminRoleModifyStatusRequestDTO {


    @NotNull(message = "角色ID不能为空")
    private Integer id;

    @NotNull(message = "角色状态不能为空")
    private Integer available;

    @NotBlank(message = "更新人不能为空")
    @Size(max = 255, message= "更新人长度不能超过255位")
    private String modifyOperator;

}
