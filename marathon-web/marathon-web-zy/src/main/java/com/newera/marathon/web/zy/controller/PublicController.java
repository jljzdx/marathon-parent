package com.newera.marathon.web.zy.controller;

import com.newera.marathon.dto.cms.inquiry.*;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminUserBaseInfoModifyRequestDTO;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminUserBaseInfoModifyResponseDTO;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminUserModifyPasswordRequestDTO;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminUserModifyPasswordResponseDTO;
import com.newera.marathon.microface.cms.admin.CmsAdminRoleMicroService;
import com.newera.marathon.microface.cms.admin.CmsAdminUserMicroService;
import com.spaking.boot.starter.cas.model.SsoUser;
import com.spaking.boot.starter.cas.utils.SsoConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private CmsAdminUserMicroService cmsAdminUserMicroService;
    @Autowired
    private CmsAdminRoleMicroService cmsAdminRoleMicroService;


    @GetMapping("/role/inquiry/select")
    @ResponseBody
    public List<Map> roleInquirySelect(){
        //调用微服务
        XfaceCmsAdminRoleSelectInquiryResponseDTO responseDTO = cmsAdminRoleMicroService.roleInquirySelect();
        List<XfaceCmsAdminRoleSelectInquiryResponseSubDTO> dataList = responseDTO.getDataList();
        //重组响应对象
        List result = new ArrayList();
        dataList.forEach(w->{
            Map map = new HashMap<>();
            map.put("value", w.getId());
            map.put("name", w.getName());
            map.put("selected", "");
            if(w.getAvailable()==0){
                map.put("disabled", "disabled");
            }else {
                map.put("disabled", "");
            }
            result.add(map);
        });
        return result;
    }

    @PostMapping("/user/base/info/modify/inquiry")
    @ResponseBody
    public XfaceCmsAdminUserBaseInfoModifyInquiryResponseDTO userBaseInfoModifyInquiry(XfaceCmsAdminUserBaseInfoModifyInquiryRequestDTO requestDTO, HttpServletRequest request){
        if(null == requestDTO.getId()){
            SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
            requestDTO.setId(user.getUserId());
        }
        XfaceCmsAdminUserBaseInfoModifyInquiryResponseDTO responseDTO = cmsAdminUserMicroService.userBaseInfoModifyInquiry(requestDTO);
        return responseDTO;
    }
    @PostMapping("/user/base/info/modify")
    public XfaceCmsAdminUserBaseInfoModifyResponseDTO userBaseInfoModify(XfaceCmsAdminUserBaseInfoModifyRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setId(user.getUserId());
        requestDTO.setModifyOperator(user.getUserName());
        XfaceCmsAdminUserBaseInfoModifyResponseDTO responseDTO = cmsAdminUserMicroService.userBaseInfoModify(requestDTO);
        return responseDTO;
    }

    @PostMapping("/user/modify/password")
    @ResponseBody
    public XfaceCmsAdminUserModifyPasswordResponseDTO userModifyPassword(XfaceCmsAdminUserModifyPasswordRequestDTO requestDTO, HttpServletRequest request){
        //获取当前用户ID
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        if(null != user){
            requestDTO.setId(user.getUserId());
        }
        requestDTO.setModifyOperator(user.getUserName());
        XfaceCmsAdminUserModifyPasswordResponseDTO responseDTO = cmsAdminUserMicroService.userModifyPassword(requestDTO);
        return responseDTO;
    }

    @PostMapping("/user/left/menu/inquiry")
    @ResponseBody
    public XfaceCmsAdminLeftMenuInquiryResponseDTO leftMenuInquiry(XfaceCmsAdminLeftMenuInquiryRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setUserName(user.getUserName());
        XfaceCmsAdminLeftMenuInquiryResponseDTO responseDTO = cmsAdminUserMicroService.leftMenuInquiry(requestDTO);
        return responseDTO;
    }
}
