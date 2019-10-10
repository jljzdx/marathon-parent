package com.newera.marathon.service.cms.controller;

import com.newera.marathon.dto.system.inquiry.*;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.service.cms.service.SysUserService;
import com.spaking.boot.starter.core.annotation.BusinessLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @BusinessLogger(key = "CMS",value = "sysUserInquiryPage")
    @PostMapping("/sys/user/inquiry/page")
    public XfaceSysUserInquiryPageResponseDTO sysUserInquiryPage(@Valid @RequestBody XfaceSysUserInquiryPageRequestDTO requestDTO){
        XfaceSysUserInquiryPageResponseDTO responseDTO = sysUserService.doSysUserInquiryPage(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysUserAddition")
    @PostMapping("/sys/user/addition")
    public XfaceSysUserAdditionResponseDTO sysUserAddition(@Valid @RequestBody XfaceSysUserAdditionRequestDTO requestDTO){
        XfaceSysUserAdditionResponseDTO responseDTO = sysUserService.doSysUserAddition(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysUserModifyInquiry")
    @PostMapping("/sys/user/modify/inquiry")
    public XfaceSysUserModifyInquiryResponseDTO sysUserModifyInquiry(@Valid @RequestBody XfaceSysUserModifyInquiryRequestDTO requestDTO){
        XfaceSysUserModifyInquiryResponseDTO responseDTO = sysUserService.doSysUserModifyInquiry(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysUserModify")
    @PostMapping("/sys/user/modify")
    public XfaceSysUserModifyResponseDTO sysUserModify(@Valid @RequestBody XfaceSysUserModifyRequestDTO requestDTO){
        XfaceSysUserModifyResponseDTO responseDTO = sysUserService.doSysUserModify(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysUserBaseInfoModifyInquiry")
    @PostMapping("/sys/user/base/info/modify/inquiry")
    public XfaceSysUserBaseInfoModifyInquiryResponseDTO sysUserBaseInfoModifyInquiry(@Valid @RequestBody XfaceSysUserBaseInfoModifyInquiryRequestDTO requestDTO){
        XfaceSysUserBaseInfoModifyInquiryResponseDTO responseDTO = sysUserService.doSysUserBaseInfoModifyInquiry(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysUserBaseInfoModify")
    @PostMapping("/sys/user/base/info/modify")
    public XfaceSysUserBaseInfoModifyResponseDTO sysUserBaseInfoModify(@Valid @RequestBody XfaceSysUserBaseInfoModifyRequestDTO requestDTO){
        XfaceSysUserBaseInfoModifyResponseDTO responseDTO = sysUserService.doSysUserBaseInfoModify(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysUserModifyStatus")
    @PostMapping("/sys/user/modify/status")
    public XfaceSysUserModifyStatusResponseDTO sysUserModifyStatus(@Valid @RequestBody XfaceSysUserModifyStatusRequestDTO requestDTO){
        XfaceSysUserModifyStatusResponseDTO responseDTO = sysUserService.doSysUserModifyStatus(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysUserModifyPassword")
    @PostMapping("/sys/user/modify/password")
    public XfaceSysUserModifyPasswordResponseDTO sysUserModifyPassword(@Valid @RequestBody XfaceSysUserModifyPasswordRequestDTO requestDTO){
        XfaceSysUserModifyPasswordResponseDTO responseDTO = sysUserService.doSysUserModifyPassword(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysUserResetPassword")
    @PostMapping("/sys/user/reset/password")
    public XfaceSysUserResetPasswordResponseDTO sysUserResetPassword(@Valid @RequestBody XfaceSysUserResetPasswordRequestDTO requestDTO){
        XfaceSysUserResetPasswordResponseDTO responseDTO = sysUserService.doSysUserResetPassword(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysLeftMenuInquiry")
    @PostMapping("/sys/user/left/menu/inquiry")
    public XfaceSysLeftMenuInquiryResponseDTO sysLeftMenuInquiry(@Valid @RequestBody XfaceSysLeftMenuInquiryRequestDTO requestDTO){
        XfaceSysLeftMenuInquiryResponseDTO responseDTO = sysUserService.doSysLeftMenuInquiry(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysPermissionsInquiry")
    @PostMapping("/sys/user/permissions/inquiry")
    public XfaceSysPermissionsInquiryResponseDTO sysPermissionsInquiry(@Valid @RequestBody XfaceSysPermissionsInquiryRequestDTO requestDTO){
        XfaceSysPermissionsInquiryResponseDTO responseDTO = sysUserService.doSysPermissionsInquiry(requestDTO);
        return responseDTO;
    }
}
