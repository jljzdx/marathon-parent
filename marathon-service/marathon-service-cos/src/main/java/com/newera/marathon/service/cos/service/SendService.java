package com.newera.marathon.service.cos.service;

import com.newera.marathon.dto.cos.maintenance.XfaceCosCheckSmsCodeRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosCheckSmsCodeResponseDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosSendSmsRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosSendSmsResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceGenearteCaptchaResponseDTO;

public interface SendService {
    XfaceCosSendSmsResponseDTO doSmsSend(XfaceCosSendSmsRequestDTO requestDTO);

    XfaceCosCheckSmsCodeResponseDTO doCheckSmsCode(XfaceCosCheckSmsCodeRequestDTO requestDTO);

    XfaceGenearteCaptchaResponseDTO doGenerateCaptcha();
}
