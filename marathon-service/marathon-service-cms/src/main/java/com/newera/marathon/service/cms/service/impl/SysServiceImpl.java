package com.newera.marathon.service.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.newera.marathon.common.model.ApplicationError;
import com.newera.marathon.common.utils.PasswordUtil;
import com.newera.marathon.dto.system.enumeration.SysUserStatus;
import com.newera.marathon.dto.system.maintenance.XfaceSysLoginRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysLoginResponseDTO;
import com.newera.marathon.service.cms.entity.SysUser;
import com.newera.marathon.service.cms.mapper.SysUserMapper;
import com.newera.marathon.service.cms.service.SysService;
import com.spaking.boot.starter.core.exception.BaseException;
import com.spaking.boot.starter.core.model.TransactionStatus;
import com.spaking.boot.starter.redis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
public class SysServiceImpl implements SysService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisUtil redisUtil;

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
        String captcha = redisUtil.get(requestDTO.getCaptchaId());
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

}
