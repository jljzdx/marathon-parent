package com.newera.marathon.base.sso.service;

import com.newera.marathon.dto.cos.maintenance.XfaceCosGenearteCaptchaResponseDTO;

/**
 * @author MicroBin
 * @description：TODO
 * @date 2020/6/16 9:38 下午
 */
public interface LoginService
{
    XfaceCosGenearteCaptchaResponseDTO doGenerateCaptcha();
}
