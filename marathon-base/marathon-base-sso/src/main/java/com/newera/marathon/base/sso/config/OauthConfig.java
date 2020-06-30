package com.newera.marathon.base.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * @author MicroBin
 * @description：认证服务器配置类
 * @date 2020/6/17 7:14 下午
 */
@Configuration
@EnableAuthorizationServer
public class OauthConfig extends AuthorizationServerConfigurerAdapter
{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    //使用jdbc管理客户端
    @Bean
    public ClientDetailsService jdbcClientDetailsService()
    {
        return new JdbcClientDetailsService(dataSource);
    }

    // 授权码管理策略
    @Bean
    public AuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }
    /**
     * 配置被允许访问此认证服务器的客户端详情信息
     * 方式1:内存方式管理
     * 方式2:数据库管理
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception
    { // 使用内存方式
        //        clients.inMemory().withClient("product-pc") // 客户端id
        //                // 客户端密码，要加密,不然一直要求登录, 获取不到令牌, 而且一定不能被泄露
        //                .secret(passwordEncoder.encode("product-secret"))
        //                // 资源id, 如商品资源，服务名称
        //                .resourceIds("product-server")
        //                // 授权类型, 可同时支持多种授权类型
        //                .authorizedGrantTypes("authorization_code", "password", "implicit", "client_credentials", "refresh_token")
        //                // 授权范围标识，哪部分资源可访问(all是标识，不是代表所有)
        //                .scopes("all")
        //                // false 跳转到授权页面手动点击授权，true 不用手动授权，直接响应授权码
        //                .autoApprove(false)
        //                // 客户端回调地址
        //                .redirectUris("http://www.mengxuegu.com/").accessTokenValiditySeconds(60 * 60 * 8)//令牌有效时间，默认12小时
        //                .refreshTokenValiditySeconds(60 * 60 * 24 * 60)//刷新令牌有效时间，默认30天
        //        ;
        clients.withClientDetails(jdbcClientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
    {
        // 密码模式要设置认证管理器
        endpoints.authenticationManager(authenticationManager);
        // 刷新令牌获取新令牌时需要
        endpoints.userDetailsService(customUserDetailsService);
        // 令牌管理策略
        endpoints.tokenStore(tokenStore).accessTokenConverter(jwtAccessTokenConverter);
        // 授权码管理策略 会产生的授权码放到 oauth_code 表中，如果这个授权码已经使用了，则对应这个表中的数据就会被删除
        endpoints.authorizationCodeServices(jdbcAuthorizationCodeServices());
    }

    /**
     * 令牌端点的安全配置
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception
    {
        // 所有人可访问 /oauth/token_key 后面要获取公钥, 默认拒绝访问
        security.tokenKeyAccess("permitAll()");
        // 认证后可访问 /oauth/check_token , 默认拒绝访问
        security.checkTokenAccess("isAuthenticated()");
    }
}
