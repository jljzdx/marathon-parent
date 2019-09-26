package com.newera.marathon.service.cms.controller;

import com.newera.marathon.dto.system.inquiry.*;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.service.cms.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/inquiry/page")
    public XfaceSysUserInquiryPageResponseDTO sysUserInquiryPage(@Valid @RequestBody XfaceSysUserInquiryPageRequestDTO requestDTO){
        XfaceSysUserInquiryPageResponseDTO responseDTO = sysUserService.doSysUserInquiryPage(requestDTO);
        return responseDTO;
    }

    @PostMapping("/addition")
    public XfaceSysUserAdditionResponseDTO sysUserAddition(@Valid @RequestBody XfaceSysUserAdditionRequestDTO requestDTO){
        XfaceSysUserAdditionResponseDTO responseDTO = sysUserService.doSysUserAddition(requestDTO);
        return responseDTO;
    }

    @PostMapping("/modify/inquiry")
    public XfaceSysUserModifyInquiryResponseDTO sysUserModifyInquiry(@Valid @RequestBody XfaceSysUserModifyInquiryRequestDTO requestDTO){
        XfaceSysUserModifyInquiryResponseDTO responseDTO = sysUserService.doSysUserModifyInquiry(requestDTO);
        return responseDTO;
    }

    @PostMapping("/modify")
    public XfaceSysUserModifyResponseDTO sysUserModify(@Valid @RequestBody XfaceSysUserModifyRequestDTO requestDTO){
        XfaceSysUserModifyResponseDTO responseDTO = sysUserService.doSysUserModify(requestDTO);
        return responseDTO;
    }
    @PostMapping("/modify/base/info")
    public XfaceSysUserBaseInfoModifyResponseDTO sysUserBaseInfoModify(@Valid @RequestBody XfaceSysUserBaseInfoModifyRequestDTO requestDTO){
        XfaceSysUserBaseInfoModifyResponseDTO responseDTO = sysUserService.doSysUserBaseInfoModify(requestDTO);
        return responseDTO;
    }

    @PostMapping("/modify/status")
    public XfaceSysUserModifyStatusResponseDTO sysUserModifyStatus(@Valid @RequestBody XfaceSysUserModifyStatusRequestDTO requestDTO){
        XfaceSysUserModifyStatusResponseDTO responseDTO = sysUserService.doSysUserModifyStatus(requestDTO);
        return responseDTO;
    }
    @PostMapping("/modify/password")
    public XfaceSysUserModifyPasswordResponseDTO sysUserModifyPassword(@Valid @RequestBody XfaceSysUserModifyPasswordRequestDTO requestDTO){
        XfaceSysUserModifyPasswordResponseDTO responseDTO = sysUserService.doSysUserModifyPassword(requestDTO);
        return responseDTO;
    }
    @PostMapping("/reset/password")
    public XfaceSysUserResetPasswordResponseDTO sysUserResetPassword(@Valid @RequestBody XfaceSysUserResetPasswordRequestDTO requestDTO){
        XfaceSysUserResetPasswordResponseDTO responseDTO = sysUserService.doSysUserResetPassword(requestDTO);
        return responseDTO;
    }
    @PostMapping("/left/menu/inquiry")
    public XfaceSysLeftMenuInquiryResponseDTO sysLeftMenuInquiry(@Valid @RequestBody XfaceSysLeftMenuInquiryRequestDTO requestDTO){
        XfaceSysLeftMenuInquiryResponseDTO responseDTO = sysUserService.doSysLeftMenuInquiry(requestDTO);
        return responseDTO;
    }
}
