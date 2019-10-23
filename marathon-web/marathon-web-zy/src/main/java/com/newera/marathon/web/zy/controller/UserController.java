package com.newera.marathon.web.zy.controller;

import com.newera.marathon.common.web.ListResponseConverter;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminUserInquiryPageRequestDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminUserInquiryPageResponseDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminUserModifyInquiryRequestDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminUserModifyInquiryResponseDTO;
import com.newera.marathon.dto.cms.maintenance.*;
import com.newera.marathon.microface.cms.admin.CmsAdminUserMicroService;
import com.newera.marathon.web.zy.model.WebPage;
import com.spaking.boot.starter.cas.model.SsoUser;
import com.spaking.boot.starter.cas.utils.SsoConstant;
import com.spaking.boot.starter.core.annotation.NeedAuthorize;
import com.spaking.boot.starter.core.model.PageModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private CmsAdminUserMicroService cmsAdminUserMicroService;
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        model.addAttribute("user", user);
        return "index";
    }
    @GetMapping("/home/tab/html")
    public String homeTabHtml(){
        return "homeTab";
    }
    @GetMapping("/user/list/html")
    public String userListHtml(){
        return "user/userList";
    }
    @GetMapping("/user/add/html")
    public String userAddHtml(){
        return "user/userAdd";
    }
    @GetMapping("/user/edit/html")
    public String userEditHtml(){
        return "user/userEdit";
    }
    @GetMapping("/user/base/info/html")
    public String userBaseInfoHtml(){
        return "user/userBaseInfo";
    }
    @GetMapping("/user/modify/password/html")
    public String userModifyPasswordHtml(){
        return "user/userModifyPassword";
    }


    @NeedAuthorize(value = "user:list")
    @PostMapping("/user/inquiry/page")
    @ResponseBody
    public Map userInquiryPage(WebPage page, String userName, String loginTime){
        XfaceCmsAdminUserInquiryPageRequestDTO requestDTO = new XfaceCmsAdminUserInquiryPageRequestDTO();
        //组装分页对象
        PageModel pageModel = new PageModel();
        pageModel.setCurrent(page.getPage());
        pageModel.setSize(page.getLimit());
        pageModel.setField(page.getField());
        pageModel.setOrder(page.getType());
        requestDTO.setPage(pageModel);
        //查询条件
        requestDTO.setUserName(userName);
        //日期处理
        if(StringUtils.isNotBlank(loginTime)){
            String [] startEndTime = loginTime.split(" - ");
            requestDTO.setStartDateTime(startEndTime[0].trim());
            requestDTO.setEndDateTime(startEndTime[1].trim());
        }
        //调用微服务
        XfaceCmsAdminUserInquiryPageResponseDTO responseDTO = cmsAdminUserMicroService.userInquiryPage(requestDTO);
        //重组响应对象
        return ListResponseConverter.listResponseConverter(responseDTO.getTransactionStatus().isSuccess(),
                responseDTO.getTransactionStatus().getErrorCode(),
                responseDTO.getTransactionStatus().getReplyText(),
                responseDTO.getPage().getTotal(),
                responseDTO.getDataList());
    }

    @NeedAuthorize(value = "user:add")
    @PostMapping("/user/addition")
    @ResponseBody
    public XfaceCmsAdminUserAdditionResponseDTO userAddition(XfaceCmsAdminUserAdditionRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setCreateOperator(user.getUserName());
        XfaceCmsAdminUserAdditionResponseDTO responseDTO = cmsAdminUserMicroService.userAddition(requestDTO);
        return responseDTO;
    }
    @NeedAuthorize(value = "user:edit")
    @PostMapping("/user/modify/inquiry")
    @ResponseBody
    public XfaceCmsAdminUserModifyInquiryResponseDTO userModifyInquiry(XfaceCmsAdminUserModifyInquiryRequestDTO requestDTO, HttpServletRequest request){
        if(null == requestDTO.getId()){
            SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
            requestDTO.setId(user.getUserId());
        }
        XfaceCmsAdminUserModifyInquiryResponseDTO responseDTO = cmsAdminUserMicroService.userModifyInquiry(requestDTO);
        return responseDTO;
    }
    @NeedAuthorize(value = "user:edit")
    @PostMapping("/user/modify")
    @ResponseBody
    public XfaceCmsAdminUserModifyResponseDTO userModify(XfaceCmsAdminUserModifyRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setModifyOperator(user.getUserName());
        XfaceCmsAdminUserModifyResponseDTO responseDTO = cmsAdminUserMicroService.userModify(requestDTO);
        return responseDTO;
    }

    @NeedAuthorize(value = "user:status")
    @PostMapping("/user/modify/status")
    @ResponseBody
    public XfaceCmsAdminUserModifyStatusResponseDTO userModify(XfaceCmsAdminUserModifyStatusRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setModifyOperator(user.getUserName());
        XfaceCmsAdminUserModifyStatusResponseDTO responseDTO = cmsAdminUserMicroService.userModifyStatus(requestDTO);
        return responseDTO;
    }
    @NeedAuthorize(value = "user:pwreset")
    @PostMapping("/user/reset/password")
    @ResponseBody
    public XfaceCmsAdminUserResetPasswordResponseDTO userModifyPassword(XfaceCmsAdminUserResetPasswordRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setModifyOperator(user.getUserName());
        XfaceCmsAdminUserResetPasswordResponseDTO responseDTO = cmsAdminUserMicroService.userResetPassword(requestDTO);
        return responseDTO;
    }
}
