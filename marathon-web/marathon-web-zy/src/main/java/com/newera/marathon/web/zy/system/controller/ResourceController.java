package com.newera.marathon.web.zy.system.controller;

import com.newera.marathon.common.web.ListResponseConverter;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceLoopInquiryRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceLoopInquiryResponseDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceModifyInquiryRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceModifyInquiryResponseDTO;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.microface.cms.system.SysResourceMicroService;
import com.spaking.boot.starter.cas.model.SsoUser;
import com.spaking.boot.starter.cas.utils.SsoConstant;
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
    private SysResourceMicroService sysResourceMicroService;
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

    @PostMapping("/sys/resource/inquiry/loop")
    @ResponseBody
    public Map sysResourceInquiryPage(XfaceSysResourceLoopInquiryRequestDTO requestDTO){
        //调用微服务
        XfaceSysResourceLoopInquiryResponseDTO responseDTO = sysResourceMicroService.sysResourceInquiryLoop(requestDTO);
        //重组响应对象
        return ListResponseConverter.listResponseConverter(responseDTO.getTransactionStatus().isSuccess(),
                responseDTO.getTransactionStatus().getErrorCode(),
                responseDTO.getTransactionStatus().getReplyText(),
                responseDTO.getDataList());
    }

    @PostMapping("/sys/resource/addition")
    @ResponseBody
    public XfaceSysResourceAdditionResponseDTO sysRoleAddition(XfaceSysResourceAdditionRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setCreateOperator(user.getUserName());
        XfaceSysResourceAdditionResponseDTO responseDTO = sysResourceMicroService.sysResourceAddition(requestDTO);
        return responseDTO;
    }
    @PostMapping("/sys/resource/modify/inquiry")
    @ResponseBody
    public XfaceSysResourceModifyInquiryResponseDTO sysRoleModifyInquiry(XfaceSysResourceModifyInquiryRequestDTO requestDTO){
        XfaceSysResourceModifyInquiryResponseDTO responseDTO = sysResourceMicroService.sysResourceModifyInquiry(requestDTO);
        return responseDTO;
    }
    @PostMapping("/sys/resource/modify")
    @ResponseBody
    public XfaceSysResourceModifyResponseDTO sysRoleModify(XfaceSysResourceModifyRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setModifyOperator(user.getUserName());
        XfaceSysResourceModifyResponseDTO responseDTO = sysResourceMicroService.sysResourceModify(requestDTO);
        return responseDTO;
    }
    @PostMapping("/sys/resource/modify/status")
    @ResponseBody
    public XfaceSysResourceModifyStatusResponseDTO sysRoleModifyStatus(XfaceSysResourceModifyStatusRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setModifyOperator(user.getUserName());
        XfaceSysResourceModifyStatusResponseDTO responseDTO = sysResourceMicroService.sysResourceModifyStatus(requestDTO);
        return responseDTO;
    }
}
