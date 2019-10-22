package com.newera.marathon.dto.cms.inquiry;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class XfaceCmsAdminResourceModifyInquiryRequestDTO {

    @NotNull(message = "资源ID不能为空")
    private Integer id;
}
