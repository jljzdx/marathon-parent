package com.newera.marathon.base.sso.service;


import com.newera.marathon.dto.cos.maintenance.XfaceCosGenearteCaptchaResponseDTO;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminLoginResponseDTO;

public interface UserService {

    XfaceCmsAdminLoginResponseDTO findUser(String username, String password, String captchaId, String captchaCode);

    XfaceCosGenearteCaptchaResponseDTO doGenerateCaptcha();

}
