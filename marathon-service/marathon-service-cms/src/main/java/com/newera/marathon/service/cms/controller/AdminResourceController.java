package com.newera.marathon.service.cms.controller;


import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceLoopInquiryRequestDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceLoopInquiryResponseDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceModifyInquiryRequestDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceModifyInquiryResponseDTO;
import com.newera.marathon.dto.cms.maintenance.*;
import com.newera.marathon.service.cms.service.AdminResourceService;
import com.spaking.boot.starter.core.annotation.BusinessLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MicroBin
 * @since 2019-08-14
 */
@RestController
public class AdminResourceController {

    @Autowired
    private AdminResourceService adminResourceService;


    @BusinessLogger(key = "CMS",value = "resourceInquiryLoop")
    @PostMapping("/cms/admin/resource/inquiry/loop")
    public XfaceCmsAdminResourceLoopInquiryResponseDTO resourceInquiryLoop(@Valid @RequestBody XfaceCmsAdminResourceLoopInquiryRequestDTO requestDTO){
        XfaceCmsAdminResourceLoopInquiryResponseDTO responseDTO = adminResourceService.doResourceInquiryLoop(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "resourceAddition")
    @PostMapping("/cms/admin/resource/addition")
    public XfaceCmsAdminResourceAdditionResponseDTO resourceAddition(@Valid @RequestBody XfaceCmsAdminResourceAdditionRequestDTO requestDTO){
        XfaceCmsAdminResourceAdditionResponseDTO responseDTO = adminResourceService.doResourceAddition(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "resourceModifyInquiry")
    @PostMapping("/cms/admin/resource/modify/inquiry")
    public XfaceCmsAdminResourceModifyInquiryResponseDTO resourceModifyInquiry(@Valid @RequestBody XfaceCmsAdminResourceModifyInquiryRequestDTO requestDTO){
        XfaceCmsAdminResourceModifyInquiryResponseDTO responseDTO = adminResourceService.doResourceModifyInquiry(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "resourceModify")
    @PostMapping("/cms/admin/resource/modify")
    public XfaceCmsAdminResourceModifyResponseDTO resourceModify(@Valid @RequestBody XfaceCmsAdminResourceModifyRequestDTO requestDTO){
        XfaceCmsAdminResourceModifyResponseDTO responseDTO = adminResourceService.doResourceModify(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "resourceModifyStatus")
    @PostMapping("/cms/admin/resource/modify/status")
    public XfaceCmsAdminResourceModifyStatusResponseDTO resourceModifyStatus(@Valid @RequestBody XfaceCmsAdminResourceModifyStatusRequestDTO requestDTO){
        XfaceCmsAdminResourceModifyStatusResponseDTO responseDTO = adminResourceService.doResourceModifyStatus(requestDTO);
        return responseDTO;
    }
}
