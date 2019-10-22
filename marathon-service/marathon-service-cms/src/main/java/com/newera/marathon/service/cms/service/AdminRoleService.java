package com.newera.marathon.service.cms.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.newera.marathon.dto.cms.inquiry.*;
import com.newera.marathon.dto.cms.maintenance.*;
import com.newera.marathon.service.cms.entity.AdminRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MicroBin
 * @since 2019-04-20
 */
public interface AdminRoleService extends IService<AdminRole> {

    XfaceCmsAdminRoleSelectInquiryResponseDTO doRoleInquirySelect();

    XfaceCmsAdminRoleAdditionResponseDTO doRoleAddition(XfaceCmsAdminRoleAdditionRequestDTO requestDTO);

    XfaceCmsAdminRoleInquiryPageResponseDTO doRoleInquiryPage(XfaceCmsAdminRoleInquiryPageRequestDTO requestDTO);

    XfaceCmsAdminRoleModifyResponseDTO doRoleModify(XfaceCmsAdminRoleModifyRequestDTO requestDTO);

    XfaceCmsAdminRoleModifyStatusResponseDTO doRoleModifyStatus(XfaceCmsAdminRoleModifyStatusRequestDTO requestDTO);

    XfaceCmsAdminRoleModifyInquiryResponseDTO doRoleModifyInquiry(XfaceCmsAdminRoleModifyInquiryRequestDTO requestDTO);

    XfaceCmsAdminRoleAuthInquiryResponseDTO doRoleAuthInquiry(XfaceCmsAdminRoleAuthInquiryRequestDTO requestDTO);

    XfaceCmsAdminRoleAuthResponseDTO doRoleAuth(XfaceCmsAdminRoleAuthRequestDTO requestDTO);
}
