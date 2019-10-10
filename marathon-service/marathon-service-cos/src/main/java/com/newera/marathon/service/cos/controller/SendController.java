package com.newera.marathon.service.cos.controller;

import com.newera.marathon.dto.cos.maintenance.XfaceCosCheckSmsCodeRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosCheckSmsCodeResponseDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosSendSmsRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosSendSmsResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceGenearteCaptchaResponseDTO;
import com.newera.marathon.service.cos.service.SendService;
import com.spaking.boot.starter.core.annotation.BusinessLogger;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SendController {

    @Autowired
    private SendService sendService;

    @BusinessLogger(key = "COS",value = "sendSms")
    @ApiOperation(value="发送短信", notes="发送短信")
    @ApiImplicitParam(name = "requestDTO", value = "入参对象", dataType = "XfaceCosSendSmsRequestDTO")
    @PostMapping("/sms/send")
    public XfaceCosSendSmsResponseDTO sendSms(@Valid @RequestBody XfaceCosSendSmsRequestDTO requestDTO){
        XfaceCosSendSmsResponseDTO responseDTO = sendService.doSmsSend(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "COS",value = "checkSmsCode")
    @ApiOperation(value="短信验证码校验", notes="短信验证码校验")
    @ApiImplicitParam(name = "requestDTO", value = "入参对象", dataType = "XfaceCosCheckSmsCodeRequestDTO")
    @PostMapping("/sms/code/check")
    public XfaceCosCheckSmsCodeResponseDTO checkSmsCode(@Valid @RequestBody XfaceCosCheckSmsCodeRequestDTO requestDTO){
        XfaceCosCheckSmsCodeResponseDTO responseDTO = sendService.doCheckSmsCode(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "COS",value = "generateCaptcha")
    @ApiOperation(value="生成图形验证码", notes="生成图形验证码")
    @PostMapping("/generate/captcha")
    public XfaceGenearteCaptchaResponseDTO generateCaptcha(){
        XfaceGenearteCaptchaResponseDTO responseDTO = sendService.doGenerateCaptcha();
        return responseDTO;
    }
}
