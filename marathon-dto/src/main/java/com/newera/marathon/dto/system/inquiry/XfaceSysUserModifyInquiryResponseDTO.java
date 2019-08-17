package com.newera.marathon.dto.system.inquiry;


import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class XfaceSysUserModifyInquiryResponseDTO extends GenericResponseDTO {

    private Integer id;

    private Integer gender;

    private String userName;

    private String mobile;

    private String realName;

    private String email;

    private List<XfaceSysUserModifyInquiryResponseSubDTO> dataList;

}
