package com.newera.marathon.service.cms.controller;

import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminLoginRequestDTO;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminLoginResponseDTO;
import com.newera.marathon.microface.cos.CosMsgLogMicroService;
import com.newera.marathon.mq.pojo.MailSend;
import com.newera.marathon.mq.producer.MailSendProducer;
import com.newera.marathon.service.cms.service.CmsService;
import com.spaking.boot.starter.core.annotation.BusinessLogger;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CmsController {

    @Autowired
    private CosMsgLogMicroService cosMsgLogMicroService;
    @Autowired
    private CmsService cmsService;

    @Autowired
    private MailSendProducer mailSendProducer;


    @BusinessLogger(key = "CMS",value = "loginAuth")
    @ApiOperation(value="后台用户登陆认证", notes="后台用户登陆认证")
    @ApiImplicitParam(name = "requestDTO", value = "入参对象", dataType = "XfaceCmsAdminLoginRequestDTO")
    @PostMapping("/cms/login/auth")
    public XfaceCmsAdminLoginResponseDTO loginAuth(@Valid @RequestBody XfaceCmsAdminLoginRequestDTO requestDTO){
        XfaceCmsAdminLoginResponseDTO responseDTO = cmsService.doLoginAuth(requestDTO);
        return responseDTO;
    }

    @PostMapping("/test/mq")
    public void testmq(){
        MailSend MailSend = new MailSend();
        MailSend.setToMail("996041341@qq.com");
        mailSendProducer.send(MailSend);
    }
}
