package com.newera.marathon.service.cms.controller;

import com.newera.marathon.dto.system.maintenance.XfaceGenearteCaptchaResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysLoginRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysLoginResponseDTO;
import com.newera.marathon.service.cms.service.SysService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SysController {
    @Autowired
    private SysService sysService;

    @ApiOperation(value="后台用户登陆认证", notes="后台用户登陆认证")
    @ApiImplicitParam(name = "requestDTO", value = "入参对象", dataType = "XfaceSysLoginRequestDTO")
    @PostMapping("/sys/login/auth")
    public XfaceSysLoginResponseDTO sysLoginAuth(@Valid @RequestBody XfaceSysLoginRequestDTO requestDTO){
        XfaceSysLoginResponseDTO responseDTO = sysService.doSysLoginAuth(requestDTO);
        return responseDTO;
    }
    @ApiOperation(value="生成图形验证码", notes="生成图形验证码")
    @PostMapping("/sys/generate/captcha")
    public XfaceGenearteCaptchaResponseDTO generateCaptcha(){
        XfaceGenearteCaptchaResponseDTO responseDTO = sysService.doGenerateCaptcha();
        return responseDTO;
    }
}
