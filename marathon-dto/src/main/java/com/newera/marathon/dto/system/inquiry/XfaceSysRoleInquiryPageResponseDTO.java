package com.newera.marathon.dto.system.inquiry;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import com.spaking.boot.starter.core.model.PageModel;
import lombok.Data;

import java.util.List;

@Data
public class XfaceSysRoleInquiryPageResponseDTO extends GenericResponseDTO {

    private PageModel page;

    private List<XfaceSysRoleInquiryPageResponseSubDTO> dataList;

}
