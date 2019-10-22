package com.newera.marathon.service.cms.service;

import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminLoginRequestDTO;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminLoginResponseDTO;

public interface CmsService {

    XfaceCmsAdminLoginResponseDTO doLoginAuth(XfaceCmsAdminLoginRequestDTO requestDTO);

}
