package com.newera.marathon.base.sso.service.impl;

import com.newera.marathon.base.sso.service.UserService;
import com.newera.marathon.dto.cos.maintenance.XfaceCosGenearteCaptchaResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysLoginRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysLoginResponseDTO;
import com.newera.marathon.microface.cos.SendMicroService;
import com.newera.marathon.microface.cms.system.SysMicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysMicroService sysMicroService;
    @Autowired
    private SendMicroService sendMicroService;

    @Override
    public XfaceSysLoginResponseDTO findUser(String username, String password, String captchaId, String captchaCode) {
        XfaceSysLoginRequestDTO requestDTO = new XfaceSysLoginRequestDTO();
        requestDTO.setUserName(username);
        requestDTO.setPassword(password);
        requestDTO.setCaptchaId(captchaId);
        requestDTO.setCaptchaCode(captchaCode);
        XfaceSysLoginResponseDTO responseDTO = sysMicroService.sysLoginAuth(requestDTO);
        return responseDTO;
    }

    @Override
    public XfaceCosGenearteCaptchaResponseDTO doGenerateCaptcha() {
        XfaceCosGenearteCaptchaResponseDTO responseDTO = sendMicroService.generateCaptcha();
        return responseDTO;
    }

}
