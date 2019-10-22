package com.newera.marathon.dto.cms.maintenance;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

@Data
public class XfaceCmsAdminLoginResponseDTO extends GenericResponseDTO {

    private Integer id;

    private String userName;

    private String realName;

    private String email;

}
