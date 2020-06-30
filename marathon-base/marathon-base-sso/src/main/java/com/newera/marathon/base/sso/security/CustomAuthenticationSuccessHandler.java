package com.newera.marathon.base.sso.security;

import com.alibaba.fastjson.JSON;
import com.newera.marathon.base.sso.emuns.LoginResponseType;
import com.newera.marathon.base.sso.properties.SecurityProperties;
import com.spaking.boot.starter.core.model.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MicroBin
 * @description：认证成功处理器
 * @date 2020/6/16 12:13 下午
 */
@Component("customAuthenticationSuccessHandler")
//public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        if (LoginResponseType.JSON.equals(securityProperties.getAuthentication().getLoginType()))
        {
            // 认证成功后，响应JSON字符串
            /*MengxueguResult result = MengxueguResult.ok("认证成功");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(result.toJsonString());*/
            TransactionStatus transactionStatus = new TransactionStatus();
            response.setContentType("application/json;charset=UTF-8");
            String result = JSON.toJSONString(transactionStatus);
            response.getWriter().write(result);
        }
        else
        {
            //重定向到上次请求的地址上，引发跳转到认证页面的地址
            logger.info("authentication: " + JSON.toJSONString(authentication));
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
