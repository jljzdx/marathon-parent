package com.newera.marathon.dto.cms.inquiry;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import com.spaking.boot.starter.core.model.PageModel;
import lombok.Data;

import java.util.List;

@Data
public class XfaceCmsAdminUserInquiryPageResponseDTO extends GenericResponseDTO {

    private PageModel page;

    private List<XfaceCmsAdminUserInquiryPageResponseSubDTO> dataList;

}
