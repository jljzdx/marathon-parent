package com.newera.marathon.service.system.controller;

import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.service.system.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SysController {
    @Autowired
    private SysService sysService;

    @PostMapping("/sys/login/auth")
    public XfaceSysLoginAuthResponseDTO sysLoginAuth(@Valid @RequestBody XfaceSysLoginAuthRequestDTO requestDTO){
        XfaceSysLoginAuthResponseDTO responseDTO = sysService.doSysLoginAuth(requestDTO);
        return responseDTO;
    }
    @PostMapping("/sys/generate/captcha")
    public XfaceGenearteCaptchaResponseDTO generateCaptcha(){
        XfaceGenearteCaptchaResponseDTO responseDTO = sysService.doGenerateCaptcha();
        return responseDTO;
    }
}
