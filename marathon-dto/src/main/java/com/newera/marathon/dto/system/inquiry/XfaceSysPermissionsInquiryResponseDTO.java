package com.newera.marathon.dto.system.inquiry;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class XfaceSysPermissionsInquiryResponseDTO extends GenericResponseDTO {

    private List<String> permissions;

}
