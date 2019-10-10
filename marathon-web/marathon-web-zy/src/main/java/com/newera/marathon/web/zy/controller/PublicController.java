package com.newera.marathon.web.zy.controller;

import com.newera.marathon.dto.system.inquiry.*;
import com.newera.marathon.dto.system.maintenance.XfaceSysUserBaseInfoModifyRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysUserBaseInfoModifyResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysUserModifyPasswordRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysUserModifyPasswordResponseDTO;
import com.newera.marathon.microface.cms.system.SysRoleMicroService;
import com.newera.marathon.microface.cms.system.SysUserMicroService;
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
    private SysUserMicroService sysUserMicroService;
    @Autowired
    private SysRoleMicroService sysRoleMicroService;


    @GetMapping("/role/inquiry/select")
    @ResponseBody
    public List<Map> sysRoleInquirySelect(){
        //调用微服务
        XfaceSysRoleInquirySelectResponseDTO responseDTO = sysRoleMicroService.sysRoleInquirySelect();
        List<XfaceSysRoleInquirySelectResponseSubDTO> dataList = responseDTO.getDataList();
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
    public XfaceSysUserBaseInfoModifyInquiryResponseDTO sysUserBaseInfoModifyInquiry(XfaceSysUserBaseInfoModifyInquiryRequestDTO requestDTO, HttpServletRequest request){
        if(null == requestDTO.getId()){
            SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
            requestDTO.setId(user.getUserId());
        }
        XfaceSysUserBaseInfoModifyInquiryResponseDTO responseDTO = sysUserMicroService.sysUserBaseInfoModifyInquiry(requestDTO);
        return responseDTO;
    }
    @PostMapping("/user/base/info/modify")
    public XfaceSysUserBaseInfoModifyResponseDTO sysUserBaseInfoModify(XfaceSysUserBaseInfoModifyRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setId(user.getUserId());
        requestDTO.setModifyOperator(user.getUserName());
        XfaceSysUserBaseInfoModifyResponseDTO responseDTO = sysUserMicroService.sysUserBaseInfoModify(requestDTO);
        return responseDTO;
    }

    @PostMapping("/user/modify/password")
    @ResponseBody
    public XfaceSysUserModifyPasswordResponseDTO sysUserModifyPassword(XfaceSysUserModifyPasswordRequestDTO requestDTO, HttpServletRequest request){
        //获取当前用户ID
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        if(null != user){
            requestDTO.setId(user.getUserId());
        }
        requestDTO.setModifyOperator(user.getUserName());
        XfaceSysUserModifyPasswordResponseDTO responseDTO = sysUserMicroService.sysUserModifyPassword(requestDTO);
        return responseDTO;
    }

    @PostMapping("/user/left/menu/inquiry")
    @ResponseBody
    public XfaceSysLeftMenuInquiryResponseDTO sysLeftMenuInquiry(XfaceSysLeftMenuInquiryRequestDTO requestDTO, HttpServletRequest request){
        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setUserName(user.getUserName());
        XfaceSysLeftMenuInquiryResponseDTO responseDTO = sysUserMicroService.sysLeftMenuInquiry(requestDTO);
        return responseDTO;
    }
}
