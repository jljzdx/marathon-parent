package com.newera.marathon.service.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceInquiryLoopRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceInquiryLoopResponseDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceModifyInquiryRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceModifyInquiryResponseDTO;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.service.system.entity.SysResource;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MicroBin
 * @since 2019-08-14
 */
public interface SysResourceService extends IService<SysResource> {

    XfaceSysResourceInquiryLoopResponseDTO doSysResourceInquiryLoop(XfaceSysResourceInquiryLoopRequestDTO requestDTO);

    XfaceSysResourceAdditionResponseDTO doSysResourceAddition(XfaceSysResourceAdditionRequestDTO requestDTO);

    XfaceSysResourceModifyInquiryResponseDTO doSysResourceModifyInquiry(XfaceSysResourceModifyInquiryRequestDTO requestDTO);

    XfaceSysResourceModifyResponseDTO doSysResourceModify(XfaceSysResourceModifyRequestDTO requestDTO);

    XfaceSysResourceModifyStatusResponseDTO doSysResourceModifyStatus(XfaceSysResourceModifyStatusRequestDTO requestDTO);
}
