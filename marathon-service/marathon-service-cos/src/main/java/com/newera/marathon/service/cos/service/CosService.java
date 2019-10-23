package com.newera.marathon.service.cos.service;

import com.newera.marathon.dto.cos.maintenance.*;

public interface CosService {
    XfaceCosSendSmsResponseDTO doSmsSend(XfaceCosSendSmsRequestDTO requestDTO);

    XfaceCosCheckSmsCodeResponseDTO doCheckSmsCode(XfaceCosCheckSmsCodeRequestDTO requestDTO);

    XfaceCosGenearteCaptchaResponseDTO doGenerateCaptcha();

    XfaceCosMailSendResponseDTO doMailSend(XfaceCosMailSendRequestDTO requestDTO);
}
