package com.newera.marathon.base.sso.service.impl;

import com.newera.marathon.base.sso.service.LoginService;
import com.newera.marathon.common.utils.CaptchaCodeUtil;
import com.newera.marathon.common.utils.RandomUtil;
import com.newera.marathon.dto.cos.maintenance.XfaceCosGenearteCaptchaResponseDTO;
import com.spaking.boot.starter.core.model.TransactionStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author MicroBin
 * @description：TODO
 * @date 2020/6/16 9:39 下午
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService
{
    @Override
    public XfaceCosGenearteCaptchaResponseDTO doGenerateCaptcha()
    {
        XfaceCosGenearteCaptchaResponseDTO responseDTO = new XfaceCosGenearteCaptchaResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        CaptchaCodeUtil captchaCodeUtil = new CaptchaCodeUtil();
        Map<String, String> map = captchaCodeUtil.getRandcode();
        String key = RandomUtil.getRandomString(12);
        String pic = map.get(CaptchaCodeUtil.PIC);
        pic = "data:image/jpg;base64,"+pic;
        responseDTO.setCaptchaCode(map.get(CaptchaCodeUtil.RANDOMSTRING));
        responseDTO.setPic(pic);
        responseDTO.setCaptchaId(key);
        responseDTO.setTransactionStatus(transactionStatus);
        return responseDTO;
    }
}
