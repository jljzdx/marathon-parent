package com.newera.marathon.dto.cms.inquiry;


import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class XfaceCmsAdminUserModifyInquiryResponseDTO extends GenericResponseDTO {

    private Integer id;

    private Integer gender;

    private String userName;

    private String mobile;

    private String realName;

    private String email;

    private List<XfaceCmsAdminUserModifyInquiryResponseSubDTO> dataList;

}
