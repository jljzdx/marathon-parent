package com.newera.marathon.dto.system.inquiry;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class XfaceSysRoleModifyInquiryRequestDTO {

    @NotNull(message = "角色ID不能为空")
    private Integer id;
}
