package com.newera.marathon.web.zy.controller;

import com.newera.marathon.common.web.ListResponseConverter;
import com.newera.marathon.dto.system.inquiry.*;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.microface.cms.system.SysRoleMicroService;
import com.spaking.boot.starter.cas.model.SsoUser;
import com.spaking.boot.starter.cas.utils.SsoConstant;
import com.spaking.boot.starter.core.annotation.NeedAuthorize;
import com.spaking.boot.starter.core.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class RoleController {

    @Autowired
    private SysRoleMicroService sysRoleMicroService;


    @GetMapping("/role/list/html")
    public String roleListHtml(){
        return "role/roleList";
    }
    @GetMapping("/role/add/html")
    public String roleAddHtml(){
        return "role/roleAdd";
    }
    @GetMapping("/role/edit/html")
    public String roleEditHtml(){
        return "role/roleEdit";
    }
    @GetMapping("/role/auth/html")
    public String roleAuthHtml(){
        return "role/roleAuth";
    }



    @NeedAuthorize(value = "role:list")
    @PostMapping("/sys/role/inquiry/page")
    @ResponseBody
    public Map sysRoleInquiryPage(Long page, Long limit, String roleName){
        XfaceSysRoleInquiryPageRequestDTO requestDTO = new XfaceSysRoleInquiryPageRequestDTO();
        //组装分页对象
        PageModel pageModel = new PageModel();
        pageModel.setCurrent(page);
        pageModel.setSize(limit);
        requestDTO.setPage(pageModel);
        //查询条件
        requestDTO.setRoleName(roleName);
        //调用微服务
        XfaceSysRoleInquiryPageResponseDTO responseDTO = sysRoleMicroService.sysRoleInquiryPage(requestDTO);
        //重组响应对象
        return ListResponseConverter.listResponseConverter(responseDTO.getTransactionStatus().isSuccess(),
                responseDTO.getTransactionStatus().getErrorCode(),
                responseDTO.getTransactionStatus().getReplyText(),
                responseDTO.getPage().getTotal(),
                responseDTO.getDataList());
    }

    @NeedAuthorize(value = "role:add")
    @PostMapping("/sys/role/addition")
    @ResponseBody
    public XfaceSysRoleAdditionResponseDTO sysRoleAddition(XfaceSysRoleAdditionRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setCreateOperator(user.getUserName());
        XfaceSysRoleAdditionResponseDTO responseDTO = sysRoleMicroService.sysRoleAddition(requestDTO);
        return responseDTO;
    }

    @NeedAuthorize(value = "role:edit")
    @PostMapping("/sys/role/modify/inquiry")
    @ResponseBody
    public XfaceSysRoleModifyInquiryResponseDTO sysRoleModifyInquiry(XfaceSysRoleModifyInquiryRequestDTO requestDTO){
        XfaceSysRoleModifyInquiryResponseDTO responseDTO = sysRoleMicroService.sysRoleModifyInquiry(requestDTO);
        return responseDTO;
    }

    @NeedAuthorize(value = "role:edit")
    @PostMapping("/sys/role/modify")
    @ResponseBody
    public XfaceSysRoleModifyResponseDTO sysRoleModify(XfaceSysRoleModifyRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setModifyOperator(user.getUserName());
        XfaceSysRoleModifyResponseDTO responseDTO = sysRoleMicroService.sysRoleModify(requestDTO);
        return responseDTO;
    }

    @NeedAuthorize(value = "role:status")
    @PostMapping("/sys/role/modify/status")
    @ResponseBody
    public XfaceSysRoleModifyStatusResponseDTO sysRoleModifyStatus(XfaceSysRoleModifyStatusRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setModifyOperator(user.getUserName());
        XfaceSysRoleModifyStatusResponseDTO responseDTO = sysRoleMicroService.sysRoleModifyStatus(requestDTO);
        return responseDTO;
    }

    @NeedAuthorize(value = "role:auth")
    @PostMapping("/sys/role/auth/inquiry")
    @ResponseBody
    public XfaceSysRoleAuthInquiryResponseDTO sysRoleAuthInquiry(XfaceSysRoleAuthInquiryRequestDTO requestDTO){
        XfaceSysRoleAuthInquiryResponseDTO responseDTO = sysRoleMicroService.sysRoleAuthInquiry(requestDTO);
        return responseDTO;
    }

    @NeedAuthorize(value = "role:auth")
    @PostMapping("/sys/role/auth")
    @ResponseBody
    public XfaceSysRoleAuthResponseDTO sysRoleAuth(XfaceSysRoleAuthRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setOperator(user.getUserName());
        XfaceSysRoleAuthResponseDTO responseDTO = sysRoleMicroService.sysRoleAuth(requestDTO);
        return responseDTO;
    }
}
