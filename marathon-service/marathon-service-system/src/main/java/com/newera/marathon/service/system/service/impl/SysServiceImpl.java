package com.newera.marathon.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.newera.marathon.common.constant.RedisConstant;
import com.newera.marathon.common.utils.CaptchaCodeUtil;
import com.newera.marathon.common.utils.PasswordUtil;
import com.newera.marathon.common.utils.RandomUtil;
import com.newera.marathon.dto.system.enumeration.SysUserStatus;
import com.newera.marathon.dto.system.maintenance.XfaceGenearteCaptchaResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysLoginRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysLoginResponseDTO;
import com.newera.marathon.service.system.entity.SysUser;
import com.newera.marathon.service.system.mapper.SysUserMapper;
import com.newera.marathon.service.system.model.ApplicationError;
import com.newera.marathon.service.system.service.SysService;
import com.spaking.boot.starter.core.exception.BaseException;
import com.spaking.boot.starter.core.model.TransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SysServiceImpl implements SysService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    @Override
    public XfaceSysLoginResponseDTO doSysLoginAuth(XfaceSysLoginRequestDTO requestDTO) {
        log.info("doSysLoginAuth start");
        XfaceSysLoginResponseDTO responseDTO = new XfaceSysLoginResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();

        //验证用户名和密码
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",requestDTO.getUserName());
        wrapper.eq("locked", SysUserStatus._1.getCode());
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        if(null == sysUser){
            throw new BaseException(ApplicationError.USER_NAME_OR_PASSWORD_ERROR.getMessage(), ApplicationError.USER_NAME_OR_PASSWORD_ERROR.getCode());
        }
        String password = sysUser.getPassword();
        Boolean result = PasswordUtil.verify(requestDTO.getPassword(), password);
        if(!result){
            throw new BaseException(ApplicationError.USER_NAME_OR_PASSWORD_ERROR.getMessage(), ApplicationError.USER_NAME_OR_PASSWORD_ERROR.getCode());
        }
        //验证图形验证码
        String captcha = (String)redisTemplate.opsForValue().get(requestDTO.getCaptchaId());
        if(StringUtils.isBlank(captcha) || !captcha.equalsIgnoreCase(requestDTO.getCaptchaCode())){
            throw new BaseException(ApplicationError.CAPTCHA_ERROR.getMessage(), ApplicationError.CAPTCHA_ERROR.getCode());
        }
        //更新登录时间、修改时间、登录次数
        SysUser updateSysUser = new SysUser();
        Date date = new Date();
        updateSysUser.setId(sysUser.getId());
        updateSysUser.setLastLoginTime(sysUser.getLoginTime());
        updateSysUser.setLoginTime(date);
        updateSysUser.setLoginCount(sysUser.getLoginCount()+1);
        //更新
        sysUserMapper.updateById(updateSysUser);
        //返回用户信息
        BeanUtils.copyProperties(sysUser, responseDTO);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysLoginAuth end");
        return responseDTO;

    }

    @Override
    public XfaceGenearteCaptchaResponseDTO doGenerateCaptcha() {
        log.info("doGenerateCaptcha start");
        XfaceGenearteCaptchaResponseDTO responseDTO = new XfaceGenearteCaptchaResponseDTO();
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
        redisTemplate.opsForValue().set(key,map.get(CaptchaCodeUtil.RANDOMSTRING),RedisConstant.CAPTCHA_EXPIRY_SECOND, TimeUnit.SECONDS);
        log.info("doGenerateCaptcha start");
        return responseDTO;
    }

}
