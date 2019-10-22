package com.newera.marathon.dto.cms.inquiry;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

@Data
public class XfaceCmsAdminRoleModifyInquiryResponseDTO extends GenericResponseDTO {

    private Integer id;

    private String roleName;

    private String remark;

}
