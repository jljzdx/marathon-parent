package com.newera.marathon.service.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceLoopInquiryRequestDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceLoopInquiryResponseDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceModifyInquiryRequestDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceModifyInquiryResponseDTO;
import com.newera.marathon.dto.cms.maintenance.*;
import com.newera.marathon.service.cms.entity.AdminResource;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MicroBin
 * @since 2019-08-14
 */
public interface AdminResourceService extends IService<AdminResource> {

    XfaceCmsAdminResourceLoopInquiryResponseDTO doResourceInquiryLoop(XfaceCmsAdminResourceLoopInquiryRequestDTO requestDTO);

    XfaceCmsAdminResourceAdditionResponseDTO doResourceAddition(XfaceCmsAdminResourceAdditionRequestDTO requestDTO);

    XfaceCmsAdminResourceModifyInquiryResponseDTO doResourceModifyInquiry(XfaceCmsAdminResourceModifyInquiryRequestDTO requestDTO);

    XfaceCmsAdminResourceModifyResponseDTO doResourceModify(XfaceCmsAdminResourceModifyRequestDTO requestDTO);

    XfaceCmsAdminResourceModifyStatusResponseDTO doResourceModifyStatus(XfaceCmsAdminResourceModifyStatusRequestDTO requestDTO);
}
