package com.newera.marathon.service.system.service;

import com.newera.marathon.dto.system.maintenance.*;

public interface SysService {

    XfaceSysLoginResponseDTO doSysLoginAuth(XfaceSysLoginRequestDTO requestDTO);

    XfaceGenearteCaptchaResponseDTO doGenerateCaptcha();
}
