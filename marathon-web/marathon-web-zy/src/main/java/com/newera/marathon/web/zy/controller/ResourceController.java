package com.newera.marathon.web.zy.controller;

import com.newera.marathon.common.web.ListResponseConverter;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceLoopInquiryRequestDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceLoopInquiryResponseDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceModifyInquiryRequestDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceModifyInquiryResponseDTO;
import com.newera.marathon.dto.cms.maintenance.*;
import com.newera.marathon.microface.cms.admin.AdminResourceMicroService;
import com.spaking.boot.starter.cas.model.SsoUser;
import com.spaking.boot.starter.cas.utils.SsoConstant;
import com.spaking.boot.starter.core.annotation.NeedAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ResourceController {

    @Autowired
    private AdminResourceMicroService adminResourceMicroService;
    @GetMapping("/resource/list/html")
    public String resourceListHtml(){
        return "resource/resourceList";
    }
    @GetMapping("/resource/add/html")
    public String resourceAddHtml(){
        return "resource/resourceAdd";
    }
    @GetMapping("/resource/edit/html")
    public String resourceEditHtml(){
        return "resource/resourceEdit";
    }


    @NeedAuthorize(value = "resource:list")
    @PostMapping("/resource/inquiry/loop")
    @ResponseBody
    public Map resourceInquiryPage(XfaceCmsAdminResourceLoopInquiryRequestDTO requestDTO){
        //调用微服务
        XfaceCmsAdminResourceLoopInquiryResponseDTO responseDTO = adminResourceMicroService.resourceInquiryLoop(requestDTO);
        //重组响应对象
        return ListResponseConverter.listResponseConverter(responseDTO.getTransactionStatus().isSuccess(),
                responseDTO.getTransactionStatus().getErrorCode(),
                responseDTO.getTransactionStatus().getReplyText(),
                responseDTO.getDataList());
    }

    @NeedAuthorize(value = "resource:add")
    @PostMapping("/resource/addition")
    @ResponseBody
    public XfaceCmsAdminResourceAdditionResponseDTO resourceAddition(XfaceCmsAdminResourceAdditionRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setCreateOperator(user.getUserName());
        XfaceCmsAdminResourceAdditionResponseDTO responseDTO = adminResourceMicroService.resourceAddition(requestDTO);
        return responseDTO;
    }

    @NeedAuthorize(value = "resource:edit")
    @PostMapping("/resource/modify/inquiry")
    @ResponseBody
    public XfaceCmsAdminResourceModifyInquiryResponseDTO resourceModifyInquiry(XfaceCmsAdminResourceModifyInquiryRequestDTO requestDTO){
        XfaceCmsAdminResourceModifyInquiryResponseDTO responseDTO = adminResourceMicroService.resourceModifyInquiry(requestDTO);
        return responseDTO;
    }

    @NeedAuthorize(value = "resource:edit")
    @PostMapping("/resource/modify")
    @ResponseBody
    public XfaceCmsAdminResourceModifyResponseDTO resourceModify(XfaceCmsAdminResourceModifyRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setModifyOperator(user.getUserName());
        XfaceCmsAdminResourceModifyResponseDTO responseDTO = adminResourceMicroService.resourceModify(requestDTO);
        return responseDTO;
    }

    @NeedAuthorize(value = "resource:status")
    @PostMapping("/resource/modify/status")
    @ResponseBody
    public XfaceCmsAdminResourceModifyStatusResponseDTO resourceModifyStatus(XfaceCmsAdminResourceModifyStatusRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setModifyOperator(user.getUserName());
        XfaceCmsAdminResourceModifyStatusResponseDTO responseDTO = adminResourceMicroService.resourceModifyStatus(requestDTO);
        return responseDTO;
    }
}
