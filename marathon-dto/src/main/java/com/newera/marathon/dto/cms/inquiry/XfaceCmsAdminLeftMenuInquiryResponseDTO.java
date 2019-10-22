package com.newera.marathon.dto.cms.inquiry;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class XfaceCmsAdminLeftMenuInquiryResponseDTO extends GenericResponseDTO {

    private List<XfaceCmsAdminLeftMenuInquiryResponseSubDTO> dataList;

    private List<String> permissions;

}
