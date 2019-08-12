package com.newera.marathon.dto.system.inquiry;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

@Data
public class XfaceSysRoleModifyInquiryResponseDTO extends GenericResponseDTO {

    private Integer id;

    private String roleName;

    private String remark;

}
