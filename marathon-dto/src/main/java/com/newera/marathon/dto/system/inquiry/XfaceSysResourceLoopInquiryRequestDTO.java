package com.newera.marathon.dto.system.inquiry;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class XfaceSysResourceLoopInquiryRequestDTO {

    /**
     * 父节点ID
     */
    @NotNull(message = "父节点不能为空")
    private Integer parentId;
}
