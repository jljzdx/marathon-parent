package com.newera.marathon.dto.cms.inquiry;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class XfaceCmsAdminRoleModifyInquiryRequestDTO {

    @NotNull(message = "角色ID不能为空")
    private Integer id;
}
