package com.newera.marathon.dto.system.inquiry;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class XfaceSysLeftMenuInquiryResponseDTO extends GenericResponseDTO {

    private List<XfaceSysLeftMenuInquiryResponseSubDTO> dataList;

    private List<String> permissions;

}
