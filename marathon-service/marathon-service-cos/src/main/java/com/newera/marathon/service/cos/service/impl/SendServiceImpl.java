package com.newera.marathon.service.cos.service.impl;

import com.newera.marathon.common.constant.RedisConstant;
import com.newera.marathon.common.model.ApplicationError;
import com.newera.marathon.common.utils.CaptchaCodeUtil;
import com.newera.marathon.common.utils.DateUtils;
import com.newera.marathon.common.utils.RandomUtil;
import com.newera.marathon.dto.cos.maintenance.*;
import com.newera.marathon.service.cos.service.MailService;
import com.newera.marathon.service.cos.service.SendService;
import com.spaking.boot.starter.core.exception.BaseException;
import com.spaking.boot.starter.core.model.TransactionStatus;
import com.spaking.boot.starter.redis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Map;

@Slf4j
@Service
public class SendServiceImpl implements SendService {
    private final String SMS_KEY = "sms_key";
    private final String SMS_COUNT_KEY = "sms_count_key";
    private final Long SMS_EXPIRE_SECONDS = 300L;
    private final Integer MAX_SEND_COUNT = 3;
    private String template = "【新时代】验证码为{0}，请在{1}页面中输入";
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MailService mailService;

    @Override
    public XfaceCosSendSmsResponseDTO doSmsSend(XfaceCosSendSmsRequestDTO requestDTO) {
        log.info("doSendPhone start");
        XfaceCosSendSmsResponseDTO responseDTO = new XfaceCosSendSmsResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        String phone = requestDTO.getPhone();
        Integer type = requestDTO.getType();

        if(type == 0){//普通发送短信
            String content = "";
            //发送短信（可以放MQ）
        }else{
            //判断操作是否太频繁，一天最多3次（使用redis计数，key='sms_count_key'+phone+type，有效期到当天0点）
            String countKey = SMS_COUNT_KEY+phone+type;
            Integer value = redisUtil.get(countKey);
            if(null != value && value >= MAX_SEND_COUNT){
                throw new BaseException(ApplicationError.SMS_OVER_MAX_COUNT.getMessage(),ApplicationError.SMS_OVER_MAX_COUNT.getCode());
            }
            log.info("countKey="+countKey+";value="+value);
            //生成短信验证码
            String verifyCode = RandomUtil.getRandom(6);
            //发送短信（可以放MQ）
            String function = "";
            if(type == 1){
                function = "注册";
            }else if(type == 2){
                function = "忘记密码";
            }
            String content = MessageFormat.format(template,new Object[]{verifyCode,function});
            //短信发送成功，次数加1
            Long expireSeconds = DateUtils.getExpireSeconds();
            if(null == value){//今天第一次
                redisUtil.setx(countKey,1,expireSeconds);
            }else{
                redisUtil.increase(countKey);
            }
            //存储短信验证码（使用redis，key='sms_key'+phone+type，有效期5分钟）
            String key = SMS_KEY+phone+type;
            log.info("key="+key+";value="+verifyCode);
            redisUtil.setx(key,verifyCode,SMS_EXPIRE_SECONDS);
        }

        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSendPhone end");
        return responseDTO;
    }

    @Override
    public XfaceCosCheckSmsCodeResponseDTO doCheckSmsCode(XfaceCosCheckSmsCodeRequestDTO requestDTO) {
        log.info("doCheckSmsCode start");
        XfaceCosCheckSmsCodeResponseDTO responseDTO = new XfaceCosCheckSmsCodeResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        String phone = requestDTO.getPhone();
        String verifyCode = requestDTO.getCode();
        Integer type = requestDTO.getType();
        String key = SMS_KEY+phone+type;
        String code = redisUtil.get(key);
        if(StringUtils.isBlank(code)){
            throw new BaseException(ApplicationError.SMS_CODE_EXPIRED.getMessage(),ApplicationError.SMS_CODE_EXPIRED.getCode());
        }
        if(!verifyCode.equalsIgnoreCase(code)){
            throw new BaseException(ApplicationError.SMS_CODE_ERROR.getMessage(),ApplicationError.SMS_CODE_ERROR.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doCheckSmsCode end");
        return responseDTO;
    }

    @Override
    public XfaceCosGenearteCaptchaResponseDTO doGenerateCaptcha() {
        log.info("doGenerateCaptcha start");
        XfaceCosGenearteCaptchaResponseDTO responseDTO = new XfaceCosGenearteCaptchaResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        CaptchaCodeUtil captchaCodeUtil = new CaptchaCodeUtil();
        Map<String, String> map = captchaCodeUtil.getRandcode();
        //把验证码存储到redis上，有效期5分钟
        String key = RandomUtil.getRandomString(12);
        String pic = map.get(CaptchaCodeUtil.PIC);
        pic = "data:image/jpg;base64,"+pic;
        responseDTO.setCaptchaCode(map.get(CaptchaCodeUtil.RANDOMSTRING));
        responseDTO.setPic(pic);
        responseDTO.setCaptchaId(key);
        responseDTO.setTransactionStatus(transactionStatus);
        Boolean result = redisUtil.setx(key,map.get(CaptchaCodeUtil.RANDOMSTRING), RedisConstant.CAPTCHA_EXPIRY_SECOND);
        log.info("doGenerateCaptcha："+result);
        log.info("doGenerateCaptcha end");
        return responseDTO;
    }

    @Override
    public XfaceCosMailSendResponseDTO doMailSend(XfaceCosMailSendRequestDTO requestDTO) {
        log.info("doMailSend start");
        XfaceCosMailSendResponseDTO responseDTO = new XfaceCosMailSendResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        String toMail = requestDTO.getToMail();
        String subject = requestDTO.getSubject();
        String content = requestDTO.getContent();
        //判断抄送人
        String cc = requestDTO.getCc();
        String [] ccs = {};
        if(StringUtils.isNotBlank(cc)){
            ccs = cc.split(",");
        }
        Integer type = requestDTO.getType();
        try {
            if(type == 3){
                mailService.sendAttachmentsMail(toMail,subject,content,requestDTO.getFilePath(),ccs);
            }else if(type == 2){
                mailService.sendHtmlMail(toMail,subject,content,ccs);
            }else{
                mailService.sendSimpleMail(toMail,subject,content,ccs);
            }
        } catch (Exception e) {
            log.info("Simple Mail Message Exception >>>>>", e);
            throw new BaseException(ApplicationError.MAIL_SEND_FAILED.getMessage(), ApplicationError.MAIL_SEND_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doMailSend end");
        return responseDTO;
    }
}
