package com.newera.marathon.web.zy.controller;

import com.newera.marathon.common.web.ListResponseConverter;
import com.newera.marathon.dto.cms.inquiry.*;
import com.newera.marathon.dto.cms.maintenance.*;
import com.newera.marathon.microface.cms.admin.CmsAdminRoleMicroService;
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
    private CmsAdminRoleMicroService cmsAdminRoleMicroService;


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
    @PostMapping("/role/inquiry/page")
    @ResponseBody
    public Map roleInquiryPage(Long page, Long limit, String roleName){
        XfaceCmsAdminRoleInquiryPageRequestDTO requestDTO = new XfaceCmsAdminRoleInquiryPageRequestDTO();
        //组装分页对象
        PageModel pageModel = new PageModel();
        pageModel.setCurrent(page);
        pageModel.setSize(limit);
        requestDTO.setPage(pageModel);
        //查询条件
        requestDTO.setRoleName(roleName);
        //调用微服务
        XfaceCmsAdminRoleInquiryPageResponseDTO responseDTO = cmsAdminRoleMicroService.roleInquiryPage(requestDTO);
        //重组响应对象
        return ListResponseConverter.listResponseConverter(responseDTO.getTransactionStatus().isSuccess(),
                responseDTO.getTransactionStatus().getErrorCode(),
                responseDTO.getTransactionStatus().getReplyText(),
                responseDTO.getPage().getTotal(),
                responseDTO.getDataList());
    }

    @NeedAuthorize(value = "role:add")
    @PostMapping("/role/addition")
    @ResponseBody
    public XfaceCmsAdminRoleAdditionResponseDTO roleAddition(XfaceCmsAdminRoleAdditionRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setCreateOperator(user.getUserName());
        XfaceCmsAdminRoleAdditionResponseDTO responseDTO = cmsAdminRoleMicroService.roleAddition(requestDTO);
        return responseDTO;
    }

    @NeedAuthorize(value = "role:edit")
    @PostMapping("/role/modify/inquiry")
    @ResponseBody
    public XfaceCmsAdminRoleModifyInquiryResponseDTO roleModifyInquiry(XfaceCmsAdminRoleModifyInquiryRequestDTO requestDTO){
        XfaceCmsAdminRoleModifyInquiryResponseDTO responseDTO = cmsAdminRoleMicroService.roleModifyInquiry(requestDTO);
        return responseDTO;
    }

    @NeedAuthorize(value = "role:edit")
    @PostMapping("/role/modify")
    @ResponseBody
    public XfaceCmsAdminRoleModifyResponseDTO roleModify(XfaceCmsAdminRoleModifyRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setModifyOperator(user.getUserName());
        XfaceCmsAdminRoleModifyResponseDTO responseDTO = cmsAdminRoleMicroService.roleModify(requestDTO);
        return responseDTO;
    }

    @NeedAuthorize(value = "role:status")
    @PostMapping("/role/modify/status")
    @ResponseBody
    public XfaceCmsAdminRoleModifyStatusResponseDTO roleModifyStatus(XfaceCmsAdminRoleModifyStatusRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setModifyOperator(user.getUserName());
        XfaceCmsAdminRoleModifyStatusResponseDTO responseDTO = cmsAdminRoleMicroService.roleModifyStatus(requestDTO);
        return responseDTO;
    }

    @NeedAuthorize(value = "role:auth")
    @PostMapping("/role/auth/inquiry")
    @ResponseBody
    public XfaceCmsAdminRoleAuthInquiryResponseDTO roleAuthInquiry(XfaceCmsAdminRoleAuthInquiryRequestDTO requestDTO){
        XfaceCmsAdminRoleAuthInquiryResponseDTO responseDTO = cmsAdminRoleMicroService.roleAuthInquiry(requestDTO);
        return responseDTO;
    }

    @NeedAuthorize(value = "role:auth")
    @PostMapping("/role/auth")
    @ResponseBody
    public XfaceCmsAdminRoleAuthResponseDTO roleAuth(XfaceCmsAdminRoleAuthRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setOperator(user.getUserName());
        XfaceCmsAdminRoleAuthResponseDTO responseDTO = cmsAdminRoleMicroService.roleAuth(requestDTO);
        return responseDTO;
    }
}
