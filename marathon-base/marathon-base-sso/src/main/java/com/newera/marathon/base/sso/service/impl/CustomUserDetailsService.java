package com.newera.marathon.base.sso.service.impl;

import com.google.common.collect.Lists;
import com.newera.marathon.base.sso.entity.AdminUser;
import com.newera.marathon.base.sso.service.ResourceService;
import com.newera.marathon.base.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author MicroBin
 * @description：security用户认证
 * @date 2020/6/18 11:01 上午
 */
@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;


    @Override
    public AdminUser loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        //根据登录名查询用户信息
        AdminUser adminUser = userService.findByUserName(userName);
        if (adminUser == null)
        {
            throw new UsernameNotFoundException("未查询到有效用户信息");
        }

        // 2. 查询该用户有哪一些权限
        List<String> adminResources = resourceService.findByUserId(userName);

        // 无权限
        if (!CollectionUtils.isEmpty(adminResources))
        {
            // 3. 封装用户信息和权限信息
            List<GrantedAuthority> authorities = Lists.newArrayList();
            for (String permission : adminResources)
            {
                //权限标识
                authorities.add(new SimpleGrantedAuthority(permission));
            }
            adminUser.setAuthorities(authorities);
        }
        return adminUser;
        //        return new User("admin", passwordEncoder.encode("123"), AuthorityUtils.commaSeparatedStringToAuthorityList("product"));
    }
}
