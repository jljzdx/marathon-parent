package com.newera.marathon.web.zy.controller;

import com.newera.marathon.common.web.ListResponseConverter;
import com.newera.marathon.dto.system.inquiry.*;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.microface.cms.system.SysUserMicroService;
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
    private SysUserMicroService sysUserMicroService;
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

    @PostMapping("/sys/user/inquiry/page")
    @ResponseBody
    public Map sysUserInquiryPage(WebPage page, String userName, String loginTime){
        XfaceSysUserInquiryPageRequestDTO requestDTO = new XfaceSysUserInquiryPageRequestDTO();
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
        XfaceSysUserInquiryPageResponseDTO responseDTO = sysUserMicroService.sysUserInquiryPage(requestDTO);
        //重组响应对象
        return ListResponseConverter.listResponseConverter(responseDTO.getTransactionStatus().isSuccess(),
                responseDTO.getTransactionStatus().getErrorCode(),
                responseDTO.getTransactionStatus().getReplyText(),
                responseDTO.getPage().getTotal(),
                responseDTO.getDataList());
    }

    @NeedAuthorize(value = "user:add")
    @PostMapping("/sys/user/addition")
    @ResponseBody
    public XfaceSysUserAdditionResponseDTO sysUserAddition(XfaceSysUserAdditionRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setCreateOperator(user.getUserName());
        XfaceSysUserAdditionResponseDTO responseDTO = sysUserMicroService.sysUserAddition(requestDTO);
        return responseDTO;
    }
    @PostMapping("/sys/user/modify/inquiry")
    @ResponseBody
    public XfaceSysUserModifyInquiryResponseDTO sysUserModifyInquiry(XfaceSysUserModifyInquiryRequestDTO requestDTO, HttpServletRequest request){
        if(null == requestDTO.getId()){
            SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
            requestDTO.setId(user.getUserId());
        }
        XfaceSysUserModifyInquiryResponseDTO responseDTO = sysUserMicroService.sysUserModifyInquiry(requestDTO);
        return responseDTO;
    }
    @PostMapping("/sys/user/modify/base/info")
    public XfaceSysUserBaseInfoModifyResponseDTO sysUserBaseInfoModify(XfaceSysUserBaseInfoModifyRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setId(user.getUserId());
        requestDTO.setModifyOperator(user.getUserName());
        XfaceSysUserBaseInfoModifyResponseDTO responseDTO = sysUserMicroService.sysUserBaseInfoModify(requestDTO);
        return responseDTO;
    }
    @NeedAuthorize(value = "user:edit")
    @PostMapping("/sys/user/modify")
    @ResponseBody
    public XfaceSysUserModifyResponseDTO sysUserModify(XfaceSysUserModifyRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setModifyOperator(user.getUserName());
        XfaceSysUserModifyResponseDTO responseDTO = sysUserMicroService.sysUserModify(requestDTO);
        return responseDTO;
    }
    @NeedAuthorize(value = "user:status")
    @PostMapping("/sys/user/modify/status")
    @ResponseBody
    public XfaceSysUserModifyStatusResponseDTO sysUserModify(XfaceSysUserModifyStatusRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setModifyOperator(user.getUserName());
        XfaceSysUserModifyStatusResponseDTO responseDTO = sysUserMicroService.sysUserModifyStatus(requestDTO);
        return responseDTO;
    }
    @PostMapping("/sys/user/modify/password")
    @ResponseBody
    public XfaceSysUserModifyPasswordResponseDTO sysUserModifyPassword(XfaceSysUserModifyPasswordRequestDTO requestDTO,HttpServletRequest request){
        //获取当前用户ID
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        if(null != user){
            requestDTO.setId(user.getUserId());
        }
        requestDTO.setModifyOperator(user.getUserName());
        XfaceSysUserModifyPasswordResponseDTO responseDTO = sysUserMicroService.sysUserModifyPassword(requestDTO);
        return responseDTO;
    }
    @PostMapping("/sys/user/reset/password")
    @ResponseBody
    public XfaceSysUserResetPasswordResponseDTO sysUserModifyPassword(XfaceSysUserResetPasswordRequestDTO requestDTO,HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setModifyOperator(user.getUserName());
        XfaceSysUserResetPasswordResponseDTO responseDTO = sysUserMicroService.sysUserResetPassword(requestDTO);
        return responseDTO;
    }
    @PostMapping("/sys/user/left/menu/inquiry")
    @ResponseBody
    public XfaceSysLeftMenuInquiryResponseDTO sysLeftMenuInquiry(XfaceSysLeftMenuInquiryRequestDTO requestDTO,HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setUserName(user.getUserName());
        XfaceSysLeftMenuInquiryResponseDTO responseDTO = sysUserMicroService.sysLeftMenuInquiry(requestDTO);
        return responseDTO;
    }
}
