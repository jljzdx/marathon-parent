package com.newera.marathon.web.zy.interceptor;

import com.newera.marathon.common.model.ApplicationError;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminPermissionsInquiryRequestDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminPermissionsInquiryResponseDTO;
import com.newera.marathon.microface.cms.admin.CmsAdminUserMicroService;
import com.spaking.boot.starter.cas.model.SsoUser;
import com.spaking.boot.starter.cas.utils.SsoConstant;
import com.spaking.boot.starter.core.annotation.NeedAuthorize;
import com.spaking.boot.starter.core.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@Slf4j
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private CmsAdminUserMicroService cmsAdminUserMicroService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            NeedAuthorize needAuthorize = ((HandlerMethod) handler).getMethodAnnotation(NeedAuthorize.class);
            if (null == needAuthorize) {
                needAuthorize = ((HandlerMethod) handler).getMethod().getDeclaringClass()
                        .getAnnotation(NeedAuthorize.class);
            }
            if (null != needAuthorize) {
                SsoUser user = (SsoUser) request.getAttribute(SsoConstant.SSO_USER);
                log.info("user>>>>>>>>>"+user);
                if (null == user) {
                    throw new BaseException(ApplicationError.LOGIN_USER_ERROR.getMessage(), ApplicationError.LOGIN_USER_ERROR.getCode());
                }
                XfaceCmsAdminPermissionsInquiryRequestDTO requestDTO = new XfaceCmsAdminPermissionsInquiryRequestDTO();
                requestDTO.setUserName(user.getUserName());
                XfaceCmsAdminPermissionsInquiryResponseDTO responseDTO = cmsAdminUserMicroService.permissionsInquiry(requestDTO);
                String value = needAuthorize.value();
                List<String> permissions = responseDTO.getPermissions();
                log.info("permissions>>>>>>>>>"+permissions);
                if(!permissions.contains(value)){
                    throw new BaseException(ApplicationError.NON_AUTHORITATIVE_INFORMATION.getMessage(), ApplicationError.NON_AUTHORITATIVE_INFORMATION.getCode());
                }
            }

        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
