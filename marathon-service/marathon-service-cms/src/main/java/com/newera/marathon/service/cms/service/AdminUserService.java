package com.newera.marathon.service.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newera.marathon.dto.cms.inquiry.*;
import com.newera.marathon.dto.cms.maintenance.*;
import com.newera.marathon.service.cms.entity.AdminUser;

public interface AdminUserService extends IService<AdminUser> {

    XfaceCmsAdminUserAdditionResponseDTO doUserAddition(XfaceCmsAdminUserAdditionRequestDTO requestDTO);

    XfaceCmsAdminUserInquiryPageResponseDTO doUserInquiryPage(XfaceCmsAdminUserInquiryPageRequestDTO requestDTO);

    XfaceCmsAdminUserModifyResponseDTO doUserModify(XfaceCmsAdminUserModifyRequestDTO requestDTO);

    XfaceCmsAdminUserBaseInfoModifyResponseDTO doUserBaseInfoModify(XfaceCmsAdminUserBaseInfoModifyRequestDTO requestDTO);

    XfaceCmsAdminUserModifyStatusResponseDTO doUserModifyStatus(XfaceCmsAdminUserModifyStatusRequestDTO requestDTO);

    XfaceCmsAdminUserModifyInquiryResponseDTO doUserModifyInquiry(XfaceCmsAdminUserModifyInquiryRequestDTO requestDTO);

    XfaceCmsAdminUserModifyPasswordResponseDTO doUserModifyPassword(XfaceCmsAdminUserModifyPasswordRequestDTO requestDTO);

    XfaceCmsAdminUserResetPasswordResponseDTO doUserResetPassword(XfaceCmsAdminUserResetPasswordRequestDTO requestDTO);

    XfaceCmsAdminLeftMenuInquiryResponseDTO doLeftMenuInquiry(XfaceCmsAdminLeftMenuInquiryRequestDTO requestDTO);

    XfaceCmsAdminPermissionsInquiryResponseDTO doPermissionsInquiry(XfaceCmsAdminPermissionsInquiryRequestDTO requestDTO);

    XfaceCmsAdminUserBaseInfoModifyInquiryResponseDTO doUserBaseInfoModifyInquiry(XfaceCmsAdminUserBaseInfoModifyInquiryRequestDTO requestDTO);
}
