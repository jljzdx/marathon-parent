package com.newera.marathon.dto.system.inquiry;


import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

@Data
public class XfaceSysResourceModifyInquiryResponseDTO extends GenericResponseDTO {

    private Integer id;

    private String name;

    private String permission;

    private String icon;

    private String url;

    private Integer priority;

    private Integer type;

    private Integer available;
}
