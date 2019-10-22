package com.newera.marathon.service.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.newera.marathon.common.model.ApplicationError;
import com.newera.marathon.common.utils.PasswordUtil;
import com.newera.marathon.dto.cms.enumeration.AdminUserStatus;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminLoginRequestDTO;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminLoginResponseDTO;
import com.newera.marathon.service.cms.entity.AdminUser;
import com.newera.marathon.service.cms.mapper.AdminUserMapper;
import com.newera.marathon.service.cms.service.CmsService;
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
public class CmsServiceImpl implements CmsService {
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Transactional
    @Override
    public XfaceCmsAdminLoginResponseDTO doLoginAuth(XfaceCmsAdminLoginRequestDTO requestDTO) {
        log.info("doLoginAuth start");
        XfaceCmsAdminLoginResponseDTO responseDTO = new XfaceCmsAdminLoginResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();

        //验证用户名和密码
        QueryWrapper<AdminUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",requestDTO.getUserName());
        wrapper.eq("locked", AdminUserStatus._1.getCode());
        AdminUser user = adminUserMapper.selectOne(wrapper);
        if(null == user){
            throw new BaseException(ApplicationError.USER_NAME_OR_PASSWORD_ERROR.getMessage(), ApplicationError.USER_NAME_OR_PASSWORD_ERROR.getCode());
        }
        String password = user.getPassword();
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
        AdminUser updateUser = new AdminUser();
        Date date = new Date();
        updateUser.setId(user.getId());
        updateUser.setLastLoginTime(user.getLoginTime());
        updateUser.setLoginTime(date);
        updateUser.setLoginCount(user.getLoginCount()+1);
        //更新
        adminUserMapper.updateById(updateUser);
        //返回用户信息
        BeanUtils.copyProperties(user, responseDTO);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doLoginAuth end");
        return responseDTO;

    }

}
