package com.newera.marathon.base.sso.service.impl;

import com.newera.marathon.base.sso.service.UserService;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminLoginRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosGenearteCaptchaResponseDTO;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminLoginResponseDTO;
import com.newera.marathon.microface.cos.SendMicroService;
import com.newera.marathon.microface.cms.admin.CmsMicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CmsMicroService cmsMicroService;
    @Autowired
    private SendMicroService sendMicroService;

    @Override
    public XfaceCmsAdminLoginResponseDTO findUser(String username, String password, String captchaId, String captchaCode) {
        XfaceCmsAdminLoginRequestDTO requestDTO = new XfaceCmsAdminLoginRequestDTO();
        requestDTO.setUserName(username);
        requestDTO.setPassword(password);
        requestDTO.setCaptchaId(captchaId);
        requestDTO.setCaptchaCode(captchaCode);
        XfaceCmsAdminLoginResponseDTO responseDTO = cmsMicroService.loginAuth(requestDTO);
        return responseDTO;
    }

    @Override
    public XfaceCosGenearteCaptchaResponseDTO doGenerateCaptcha() {
        XfaceCosGenearteCaptchaResponseDTO responseDTO = sendMicroService.generateCaptcha();
        return responseDTO;
    }

}
