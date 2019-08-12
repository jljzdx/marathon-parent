package com.newera.marathon.service.system.service;

import com.newera.marathon.dto.system.maintenance.*;

public interface SysService {

    XfaceSysLoginAuthResponseDTO doSysLoginAuth(XfaceSysLoginAuthRequestDTO requestDTO);

    XfaceGenearteCaptchaResponseDTO doGenerateCaptcha();
}
