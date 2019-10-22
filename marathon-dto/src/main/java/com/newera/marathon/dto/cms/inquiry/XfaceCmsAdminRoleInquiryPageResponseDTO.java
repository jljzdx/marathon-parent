package com.newera.marathon.dto.cms.inquiry;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import com.spaking.boot.starter.core.model.PageModel;
import lombok.Data;

import java.util.List;

@Data
public class XfaceCmsAdminRoleInquiryPageResponseDTO extends GenericResponseDTO {

    private PageModel page;

    private List<XfaceCmsAdminRoleInquiryPageResponseSubDTO> dataList;

}
