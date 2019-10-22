package com.newera.marathon.dto.cms.inquiry;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class XfaceCmsAdminPermissionsInquiryResponseDTO extends GenericResponseDTO {

    private List<String> permissions;

}
