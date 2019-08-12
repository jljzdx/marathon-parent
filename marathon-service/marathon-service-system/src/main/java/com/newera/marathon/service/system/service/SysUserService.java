package com.newera.marathon.service.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newera.marathon.dto.system.inquiry.XfaceSysUserInquiryPageRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysUserInquiryPageResponseDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysUserModifyInquiryRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysUserModifyInquiryResponseDTO;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.service.system.entity.SysUser;

public interface SysUserService  extends IService<SysUser> {

    XfaceSysUserAdditionResponseDTO doSysUserAddition(XfaceSysUserAdditionRequestDTO requestDTO);

    XfaceSysUserInquiryPageResponseDTO doSysUserInquiryPage(XfaceSysUserInquiryPageRequestDTO requestDTO);

    XfaceSysUserModifyResponseDTO doSysUserModify(XfaceSysUserModifyRequestDTO requestDTO);

    XfaceSysUserModifyStatusResponseDTO doSysUserModifyStatus(XfaceSysUserModifyStatusRequestDTO requestDTO);

    XfaceSysUserModifyInquiryResponseDTO doSysUserModifyInquiry(XfaceSysUserModifyInquiryRequestDTO requestDTO);

    XfaceSysUserModifyPasswordResponseDTO doSysUserModifyPassword(XfaceSysUserModifyPasswordRequestDTO requestDTO);

    XfaceSysUserResetPasswordResponseDTO doSysUserResetPassword(XfaceSysUserResetPasswordRequestDTO requestDTO);
}
