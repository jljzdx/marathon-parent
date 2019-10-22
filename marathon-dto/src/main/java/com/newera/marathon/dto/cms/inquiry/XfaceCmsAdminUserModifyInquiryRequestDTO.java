package com.newera.marathon.dto.cms.inquiry;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class XfaceCmsAdminUserModifyInquiryRequestDTO {

    @NotNull(message = "用户ID不能为空")
    private Integer id;
}
