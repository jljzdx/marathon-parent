package com.newera.marathon.service.system.controller;


import com.newera.marathon.dto.system.inquiry.XfaceSysResourceInquiryLoopRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceInquiryLoopResponseDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceModifyInquiryRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceModifyInquiryResponseDTO;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.service.system.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/sys/resource")
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    @PostMapping("/inquiry/loop")
    public XfaceSysResourceInquiryLoopResponseDTO sysResourceInquiryLoop(@Valid @RequestBody XfaceSysResourceInquiryLoopRequestDTO requestDTO){
        XfaceSysResourceInquiryLoopResponseDTO responseDTO = sysResourceService.doSysResourceInquiryLoop(requestDTO);
        return responseDTO;
    }

    @PostMapping("/addition")
    public XfaceSysResourceAdditionResponseDTO sysResourceAddition(@Valid @RequestBody XfaceSysResourceAdditionRequestDTO requestDTO){
        XfaceSysResourceAdditionResponseDTO responseDTO = sysResourceService.doSysResourceAddition(requestDTO);
        return responseDTO;
    }

    @PostMapping("/modify/inquiry")
    public XfaceSysResourceModifyInquiryResponseDTO sysResourceModifyInquiry(@Valid @RequestBody XfaceSysResourceModifyInquiryRequestDTO requestDTO){
        XfaceSysResourceModifyInquiryResponseDTO responseDTO = sysResourceService.doSysResourceModifyInquiry(requestDTO);
        return responseDTO;
    }

    @PostMapping("/modify")
    public XfaceSysResourceModifyResponseDTO sysResourceModify(@Valid @RequestBody XfaceSysResourceModifyRequestDTO requestDTO){
        XfaceSysResourceModifyResponseDTO responseDTO = sysResourceService.doSysResourceModify(requestDTO);
        return responseDTO;
    }

    @PostMapping("/modify/status")
    public XfaceSysResourceModifyStatusResponseDTO sysResourceModifyStatus(@Valid @RequestBody XfaceSysResourceModifyStatusRequestDTO requestDTO){
        XfaceSysResourceModifyStatusResponseDTO responseDTO = sysResourceService.doSysResourceModifyStatus(requestDTO);
        return responseDTO;
    }
}
