package com.newera.marathon.service.cms.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.newera.marathon.dto.system.inquiry.*;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.service.cms.entity.SysRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MicroBin
 * @since 2019-04-20
 */
public interface SysRoleService extends IService<SysRole> {

    XfaceSysRoleInquirySelectResponseDTO doSysRoleInquirySelect();

    XfaceSysRoleAdditionResponseDTO doSysRoleAddition(XfaceSysRoleAdditionRequestDTO requestDTO);

    XfaceSysRoleInquiryPageResponseDTO doSysRoleInquiryPage(XfaceSysRoleInquiryPageRequestDTO requestDTO);

    XfaceSysRoleModifyResponseDTO doSysRoleModify(XfaceSysRoleModifyRequestDTO requestDTO);

    XfaceSysRoleModifyStatusResponseDTO doSysRoleModifyStatus(XfaceSysRoleModifyStatusRequestDTO requestDTO);

    XfaceSysRoleModifyInquiryResponseDTO doSysRoleModifyInquiry(XfaceSysRoleModifyInquiryRequestDTO requestDTO);

    XfaceSysRoleAuthInquiryResponseDTO doSysRoleAuthInquiry(XfaceSysRoleAuthInquiryRequestDTO requestDTO);

    XfaceSysRoleAuthResponseDTO doSysRoleAuth(XfaceSysRoleAuthRequestDTO requestDTO);
}
