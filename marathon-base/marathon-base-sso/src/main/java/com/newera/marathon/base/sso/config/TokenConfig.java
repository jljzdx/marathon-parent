package com.newera.marathon.base.sso.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;

/**
 * @author MicroBin
 * @description：使用redis管理令牌
 * @date 2020/6/18 12:56 下午
 */
@Configuration
public class TokenConfig
{
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;

    //JWT密钥
    private static final String SIGNING_KEY = "jwt-key";

    // jdbc管理token
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource(){
        return new DruidDataSource();
    }

    @Bean // 在 JwtAccessTokenConverter 中定义 Jwt 签名密码
    public JwtAccessTokenConverter jwtAccessTokenConverter()
    {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 对称密钥来签署我们的令牌，资源服务器也将使用此秘钥来验证准码性
        //        converter.setSigningKey(SIGNING_KEY);
        //非对称加密
        //读取oauth2.jks文件中的私钥，第二个参数：是口令oauth2
        KeyStoreKeyFactory storeKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("oauth2.jks"), "oauth2".toCharArray());
        converter.setKeyPair(storeKeyFactory.getKeyPair("oauth2"));
        return converter;
    }

    @Bean
    public TokenStore tokenStore()
    {
        // Redis 管理令牌
        //        return new RedisTokenStore(redisConnectionFactory);
        return new JwtTokenStore(jwtAccessTokenConverter()); // 参数不要少了括号
    }
}
