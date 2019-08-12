package com.newera.marathon.dto.system.maintenance;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import lombok.Data;

@Data
public class XfaceSysLoginAuthResponseDTO extends GenericResponseDTO {

    private Integer id;

    private String userName;

    private String realName;

    private String email;

}
