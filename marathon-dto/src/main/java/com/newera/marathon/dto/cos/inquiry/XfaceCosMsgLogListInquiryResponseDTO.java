package com.newera.marathon.dto.cos.inquiry;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class XfaceCosMsgLogListInquiryResponseDTO extends GenericResponseDTO {

    private List<XfaceCosMsgLogListInquiryResponseSubDTO> dataList;
}
