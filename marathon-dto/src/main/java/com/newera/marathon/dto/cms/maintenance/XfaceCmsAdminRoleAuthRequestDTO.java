package com.newera.marathon.dto.cms.maintenance;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class XfaceCmsAdminRoleAuthRequestDTO {


    @NotNull(message = "角色ID不能为空")
    private Integer roleId;

    private String resourceIds;

    @NotBlank(message = "操作人不能为空")
    @Size(max = 255, message= "操作人长度不能超过255位")
    private String operator;
}
