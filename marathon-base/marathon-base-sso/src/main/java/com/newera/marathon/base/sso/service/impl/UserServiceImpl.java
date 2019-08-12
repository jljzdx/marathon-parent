package com.newera.marathon.base.sso.service.impl;

import com.newera.marathon.base.sso.service.UserService;
import com.newera.marathon.dto.system.maintenance.XfaceGenearteCaptchaResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysLoginAuthRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysLoginAuthResponseDTO;
import com.newera.marathon.microface.system.SysMicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysMicroService sysMicroService;

    @Override
    public XfaceSysLoginAuthResponseDTO findUser(String username, String password,String captchaId,String captchaCode) {
        XfaceSysLoginAuthRequestDTO requestDTO = new XfaceSysLoginAuthRequestDTO();
        requestDTO.setUserName(username);
        requestDTO.setPassword(password);
        requestDTO.setCaptchaId(captchaId);
        requestDTO.setCaptchaCode(captchaCode);
        XfaceSysLoginAuthResponseDTO responseDTO = sysMicroService.sysLoginAuth(requestDTO);
        return responseDTO;
    }

    @Override
    public XfaceGenearteCaptchaResponseDTO doGenerateCaptcha() {
        XfaceGenearteCaptchaResponseDTO responseDTO = sysMicroService.generateCaptcha();
        return responseDTO;
    }

}
