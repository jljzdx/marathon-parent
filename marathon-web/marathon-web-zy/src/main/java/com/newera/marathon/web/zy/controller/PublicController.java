package com.newera.marathon.web.zy.controller;

import com.newera.marathon.dto.cms.inquiry.*;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminUserBaseInfoModifyRequestDTO;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminUserBaseInfoModifyResponseDTO;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminUserModifyPasswordRequestDTO;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminUserModifyPasswordResponseDTO;
import com.newera.marathon.microface.cms.admin.CmsAdminRoleMicroService;
import com.newera.marathon.microface.cms.admin.CmsAdminUserMicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private CmsAdminUserMicroService cmsAdminUserMicroService;
    @Autowired
    private CmsAdminRoleMicroService cmsAdminRoleMicroService;


    @GetMapping("userinfo")
    @ResponseBody
    public String getUserInfo(HttpServletRequest request, HttpSession session){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        // 如果解析到令牌就会封装到OAuth2Authentication对象
        if( !(authentication instanceof OAuth2Authentication)) {
            return null;
        }

        // 用户名,没有其他用户信息
        Object principal = authentication.getPrincipal();
        // 获取用户所拥有的权限
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        Set<String> authoritySet = AuthorityUtils.authorityListToSet(authorities);
        // 请求详情
        Object details = authentication.getDetails();

        Map<String, Object> result =  new HashMap<>();
        result.put("principal", principal);
        result.put("authorities", authoritySet);
        result.put("details", details);
        return "success";
    }
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
//            SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
//            requestDTO.setId(user.getUserId());
        }
        XfaceCmsAdminUserBaseInfoModifyInquiryResponseDTO responseDTO = cmsAdminUserMicroService.userBaseInfoModifyInquiry(requestDTO);
        return responseDTO;
    }
    @PostMapping("/user/base/info/modify")
    public XfaceCmsAdminUserBaseInfoModifyResponseDTO userBaseInfoModify(XfaceCmsAdminUserBaseInfoModifyRequestDTO requestDTO, HttpServletRequest request){
//        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
//        requestDTO.setId(user.getUserId());
//        requestDTO.setModifyOperator(user.getUserName());
        XfaceCmsAdminUserBaseInfoModifyResponseDTO responseDTO = cmsAdminUserMicroService.userBaseInfoModify(requestDTO);
        return responseDTO;
    }

    @PostMapping("/user/modify/password")
    @ResponseBody
    public XfaceCmsAdminUserModifyPasswordResponseDTO userModifyPassword(XfaceCmsAdminUserModifyPasswordRequestDTO requestDTO, HttpServletRequest request){
        //获取当前用户ID
        /*SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        if(null != user){
            requestDTO.setId(user.getUserId());
        }
        requestDTO.setModifyOperator(user.getUserName());*/
        XfaceCmsAdminUserModifyPasswordResponseDTO responseDTO = cmsAdminUserMicroService.userModifyPassword(requestDTO);
        return responseDTO;
    }

    @PostMapping("/user/left/menu/inquiry")
    @ResponseBody
    public XfaceCmsAdminLeftMenuInquiryResponseDTO leftMenuInquiry(XfaceCmsAdminLeftMenuInquiryRequestDTO requestDTO, HttpServletRequest request){
//        SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
        requestDTO.setUserName("MicroBin");
        XfaceCmsAdminLeftMenuInquiryResponseDTO responseDTO = cmsAdminUserMicroService.leftMenuInquiry(requestDTO);
        return responseDTO;
    }
}
