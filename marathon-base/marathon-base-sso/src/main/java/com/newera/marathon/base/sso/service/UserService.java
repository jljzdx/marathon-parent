package com.newera.marathon.base.sso.service;


import com.newera.marathon.dto.system.maintenance.XfaceGenearteCaptchaResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysLoginAuthResponseDTO;

public interface UserService {

    XfaceSysLoginAuthResponseDTO findUser(String username, String password, String captchaId, String captchaCode);

    XfaceGenearteCaptchaResponseDTO doGenerateCaptcha();

}
