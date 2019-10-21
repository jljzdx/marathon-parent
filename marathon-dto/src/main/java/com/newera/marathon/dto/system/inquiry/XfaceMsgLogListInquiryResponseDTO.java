package com.newera.marathon.dto.system.inquiry;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class XfaceMsgLogListInquiryResponseDTO extends GenericResponseDTO {

    private List<XfaceMsgLogListInquiryResponseSubDTO> dataList;
}
