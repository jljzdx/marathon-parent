package com.newera.marathon.base.sso.controller;

import com.newera.marathon.base.sso.service.UserService;
import com.newera.marathon.dto.cos.maintenance.XfaceCosGenearteCaptchaResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysLoginResponseDTO;
import com.spaking.boot.starter.cas.model.ReturnT;
import com.spaking.boot.starter.cas.model.SsoUser;
import com.spaking.boot.starter.cas.utils.SsoConstant;
import com.spaking.boot.starter.cas.utils.SsoLoginStore;
import com.spaking.boot.starter.cas.utils.SsoSessionIdHelper;
import com.spaking.boot.starter.cas.utils.SsoWebLoginHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {

        // login check
        SsoUser user = SsoWebLoginHelper.loginCheck(request, response);

        if (user == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("user", user);
            return "index";
        }
    }

    @RequestMapping("/generate/captcha")
    @ResponseBody
    public XfaceCosGenearteCaptchaResponseDTO generateCaptcha(){
        return userService.doGenerateCaptcha();
    }

    /**
     * Login page
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(SsoConstant.SSO_LOGIN)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response) {

        // login check
        SsoUser user = SsoWebLoginHelper.loginCheck(request, response);

        if (user != null) {

            // success redirect
            String redirectUrl = request.getParameter(SsoConstant.REDIRECT_URL);
            if (redirectUrl!=null && redirectUrl.trim().length()>0) {

                String sessionId = SsoWebLoginHelper.getSessionIdByCookie(request);
                String redirectUrlFinal = redirectUrl + "?" + SsoConstant.SSO_SESSIONID + "=" + sessionId;

                return "redirect:" + redirectUrlFinal;
            } else {
                return "redirect:/";
            }
        }

        model.addAttribute("errorMsg", request.getParameter("errorMsg"));
        model.addAttribute(SsoConstant.REDIRECT_URL, request.getParameter(SsoConstant.REDIRECT_URL));
        return "login";
    }

    /**
     * Login
     *
     * @param request
     * @param loginAccount
     * @param loginPassword
     * @param captchaId
     * @param captchaCode
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public ReturnT<String> doLogin(HttpServletRequest request,
                                   HttpServletResponse response,
                                   String loginAccount,
                                   String loginPassword,
                                   String captchaId,
                                   String captchaCode,
                                   String ifRemember) {

        boolean ifRem = (ifRemember!=null&&"on".equals(ifRemember))?true:false;

        // valid login
        XfaceSysLoginResponseDTO result = userService.findUser(loginAccount, loginPassword, captchaId, captchaCode);
        if (!result.getTransactionStatus().isSuccess()) {
            return new ReturnT(999, result.getTransactionStatus().getReplyText());
        }

        // 1、make sso service
        SsoUser user = new SsoUser();
        user.setUserId(result.getId());
        user.setUserName(result.getUserName());
        user.setRealName(result.getRealName());
        user.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        user.setExpireMinite(SsoLoginStore.getRedisExpireMinite());
        user.setExpireFreshTime(System.currentTimeMillis());

        // 2、make session id
        String sessionId = SsoSessionIdHelper.makeSessionId(user);

        // 3、login, store storeKey + cookie sessionId
        SsoWebLoginHelper.login(response, sessionId, user, ifRem);

        // 4、return, redirect sessionId
        String redirectUrl = request.getParameter(SsoConstant.REDIRECT_URL);
        String redirectUrlFinal = redirectUrl + "?" + SsoConstant.SSO_SESSIONID + "=" + sessionId;
        return new ReturnT<String>(redirectUrlFinal);

    }

    /**
     * Logout
     *
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(SsoConstant.SSO_LOGOUT)
    public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {

        // logout
        SsoWebLoginHelper.logout(request, response);

        redirectAttributes.addAttribute(SsoConstant.REDIRECT_URL, request.getParameter(SsoConstant.REDIRECT_URL));
        return "redirect:/login";
    }


}