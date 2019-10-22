package com.newera.marathon.dto.cms.inquiry;


import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

@Data
public class XfaceCmsAdminUserBaseInfoModifyInquiryResponseDTO extends GenericResponseDTO {

    private Integer id;

    private Integer gender;

    private String userName;

    private String mobile;

    private String realName;

    private String email;

    private String roles;

}
