package com.newera.marathon.dto.system.inquiry;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class XfaceSysResourceModifyInquiryRequestDTO {

    @NotNull(message = "ID不能为空")
    private Integer id;
}
