package com.newera.marathon.base.sso.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.newera.marathon.base.sso.entity.AdminUser;
import com.newera.marathon.base.sso.mapper.AdminUserMapper;
import com.newera.marathon.base.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser findByUserName(String userName)
    {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        return adminUserMapper.selectOne(queryWrapper);
    }


    /*@Override
    public XfaceCmsAdminLoginResponseDTO findUser(String username, String password, String captchaId, String captchaCode) {
        XfaceCmsAdminLoginRequestDTO requestDTO = new XfaceCmsAdminLoginRequestDTO();
        requestDTO.setUserName(username);
        requestDTO.setPassword(password);
        requestDTO.setCaptchaId(captchaId);
        requestDTO.setCaptchaCode(captchaCode);
        XfaceCmsAdminLoginResponseDTO responseDTO = cmsMicroService.loginAuth(requestDTO);
        return responseDTO;
    }*/

}
