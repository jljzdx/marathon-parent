package com.newera.marathon.service.cms.controller;

import com.newera.marathon.dto.cms.inquiry.*;
import com.newera.marathon.dto.cms.maintenance.*;
import com.newera.marathon.service.cms.service.AdminUserService;
import com.spaking.boot.starter.core.annotation.BusinessLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @BusinessLogger(key = "CMS",value = "userInquiryPage")
    @PostMapping("/cms/admin/user/inquiry/page")
    public XfaceCmsAdminUserInquiryPageResponseDTO userInquiryPage(@Valid @RequestBody XfaceCmsAdminUserInquiryPageRequestDTO requestDTO){
        XfaceCmsAdminUserInquiryPageResponseDTO responseDTO = adminUserService.doUserInquiryPage(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "userAddition")
    @PostMapping("/cms/admin/user/addition")
    public XfaceCmsAdminUserAdditionResponseDTO userAddition(@Valid @RequestBody XfaceCmsAdminUserAdditionRequestDTO requestDTO){
        XfaceCmsAdminUserAdditionResponseDTO responseDTO = adminUserService.doUserAddition(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "userModifyInquiry")
    @PostMapping("/cms/admin/user/modify/inquiry")
    public XfaceCmsAdminUserModifyInquiryResponseDTO userModifyInquiry(@Valid @RequestBody XfaceCmsAdminUserModifyInquiryRequestDTO requestDTO){
        XfaceCmsAdminUserModifyInquiryResponseDTO responseDTO = adminUserService.doUserModifyInquiry(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "userModify")
    @PostMapping("/cms/admin/user/modify")
    public XfaceCmsAdminUserModifyResponseDTO userModify(@Valid @RequestBody XfaceCmsAdminUserModifyRequestDTO requestDTO){
        XfaceCmsAdminUserModifyResponseDTO responseDTO = adminUserService.doUserModify(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "userBaseInfoModifyInquiry")
    @PostMapping("/cms/admin/user/base/info/modify/inquiry")
    public XfaceCmsAdminUserBaseInfoModifyInquiryResponseDTO userBaseInfoModifyInquiry(@Valid @RequestBody XfaceCmsAdminUserBaseInfoModifyInquiryRequestDTO requestDTO){
        XfaceCmsAdminUserBaseInfoModifyInquiryResponseDTO responseDTO = adminUserService.doUserBaseInfoModifyInquiry(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "userBaseInfoModify")
    @PostMapping("/cms/admin/user/base/info/modify")
    public XfaceCmsAdminUserBaseInfoModifyResponseDTO userBaseInfoModify(@Valid @RequestBody XfaceCmsAdminUserBaseInfoModifyRequestDTO requestDTO){
        XfaceCmsAdminUserBaseInfoModifyResponseDTO responseDTO = adminUserService.doUserBaseInfoModify(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "userModifyStatus")
    @PostMapping("/cms/admin/user/modify/status")
    public XfaceCmsAdminUserModifyStatusResponseDTO userModifyStatus(@Valid @RequestBody XfaceCmsAdminUserModifyStatusRequestDTO requestDTO){
        XfaceCmsAdminUserModifyStatusResponseDTO responseDTO = adminUserService.doUserModifyStatus(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "userModifyPassword")
    @PostMapping("/cms/admin/user/modify/password")
    public XfaceCmsAdminUserModifyPasswordResponseDTO userModifyPassword(@Valid @RequestBody XfaceCmsAdminUserModifyPasswordRequestDTO requestDTO){
        XfaceCmsAdminUserModifyPasswordResponseDTO responseDTO = adminUserService.doUserModifyPassword(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "userResetPassword")
    @PostMapping("/cms/admin/user/reset/password")
    public XfaceCmsAdminUserResetPasswordResponseDTO userResetPassword(@Valid @RequestBody XfaceCmsAdminUserResetPasswordRequestDTO requestDTO){
        XfaceCmsAdminUserResetPasswordResponseDTO responseDTO = adminUserService.doUserResetPassword(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "leftMenuInquiry")
    @PostMapping("/cms/admin/user/left/menu/inquiry")
    public XfaceCmsAdminLeftMenuInquiryResponseDTO leftMenuInquiry(@Valid @RequestBody XfaceCmsAdminLeftMenuInquiryRequestDTO requestDTO){
        XfaceCmsAdminLeftMenuInquiryResponseDTO responseDTO = adminUserService.doLeftMenuInquiry(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "permissionsInquiry")
    @PostMapping("/cms/admin/user/permissions/inquiry")
    public XfaceCmsAdminPermissionsInquiryResponseDTO permissionsInquiry(@Valid @RequestBody XfaceCmsAdminPermissionsInquiryRequestDTO requestDTO){
        XfaceCmsAdminPermissionsInquiryResponseDTO responseDTO = adminUserService.doPermissionsInquiry(requestDTO);
        return responseDTO;
    }
}
