package com.newera.marathon.dto.system.inquiry;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class XfaceSysUserModifyInquiryRequestDTO {

    @NotNull(message = "用户ID不能为空")
    private Integer id;
}
