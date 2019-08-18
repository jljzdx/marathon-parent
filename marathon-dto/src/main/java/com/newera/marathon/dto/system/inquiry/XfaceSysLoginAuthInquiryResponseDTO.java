package com.newera.marathon.dto.system.inquiry;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class XfaceSysLoginAuthInquiryResponseDTO extends GenericResponseDTO {

    private List<XfaceSysLoginAuthMenuInquiryResponseSubDTO> menuDataList;

    private List<XfaceSysLoginAuthActionInquiryResponseSubDTO> actionDataList;

}
