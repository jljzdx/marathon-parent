package com.newera.marathon.base.sso.controller;

import com.newera.marathon.base.sso.service.LoginService;
import com.newera.marathon.dto.cos.maintenance.XfaceCosGenearteCaptchaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author MicroBin
 * @description：登录相关接口
 * @date 2020/6/20 11:39 上午
 */
@Controller
public class LoginController
{
    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login/page")
    public String login(ModelAndView modelAndView)
    {
        return "login";
    }

    @PostMapping("/generate/captcha")
    @ResponseBody
    public XfaceCosGenearteCaptchaResponseDTO generateCaptcha(HttpServletRequest request)
    {
        XfaceCosGenearteCaptchaResponseDTO responseDTO = loginService.doGenerateCaptcha();
        request.getSession().setAttribute(SESSION_KEY , responseDTO.getCaptchaCode());
        return responseDTO;
    }
    @RequestMapping("/test")
    @ResponseBody
    public String test()
    {
        return "test";
    }
}
