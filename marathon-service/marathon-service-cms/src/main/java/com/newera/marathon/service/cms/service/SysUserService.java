package com.newera.marathon.service.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newera.marathon.dto.system.inquiry.*;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.service.cms.entity.SysUser;

public interface SysUserService  extends IService<SysUser> {

    XfaceSysUserAdditionResponseDTO doSysUserAddition(XfaceSysUserAdditionRequestDTO requestDTO);

    XfaceSysUserInquiryPageResponseDTO doSysUserInquiryPage(XfaceSysUserInquiryPageRequestDTO requestDTO);

    XfaceSysUserModifyResponseDTO doSysUserModify(XfaceSysUserModifyRequestDTO requestDTO);

    XfaceSysUserBaseInfoModifyResponseDTO doSysUserBaseInfoModify(XfaceSysUserBaseInfoModifyRequestDTO requestDTO);

    XfaceSysUserModifyStatusResponseDTO doSysUserModifyStatus(XfaceSysUserModifyStatusRequestDTO requestDTO);

    XfaceSysUserModifyInquiryResponseDTO doSysUserModifyInquiry(XfaceSysUserModifyInquiryRequestDTO requestDTO);

    XfaceSysUserModifyPasswordResponseDTO doSysUserModifyPassword(XfaceSysUserModifyPasswordRequestDTO requestDTO);

    XfaceSysUserResetPasswordResponseDTO doSysUserResetPassword(XfaceSysUserResetPasswordRequestDTO requestDTO);

    XfaceSysLeftMenuInquiryResponseDTO doSysLeftMenuInquiry(XfaceSysLeftMenuInquiryRequestDTO requestDTO);

    XfaceSysPermissionsInquiryResponseDTO doSysPermissionsInquiry(XfaceSysPermissionsInquiryRequestDTO requestDTO);

    XfaceSysUserBaseInfoModifyInquiryResponseDTO doSysUserBaseInfoModifyInquiry(XfaceSysUserBaseInfoModifyInquiryRequestDTO requestDTO);
}
