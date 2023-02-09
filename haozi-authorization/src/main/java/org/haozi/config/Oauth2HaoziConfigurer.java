package org.haozi.config;

import org.haozi.auth.authentication.password.UserDetailsAuthenticationPasswordProvider;
import org.haozi.auth.endpoint.provider.AuthProvider;
import org.haozi.auth.endpoint.provider.PassworderProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/6 18:32
 */
@Configuration
public class Oauth2HaoziConfigurer   {

    @Bean
    public AuthProvider passwordProvider(){
        return new PassworderProvider();
    }

    @Bean
    public AbstractUserDetailsAuthenticationProvider UserDetailsAuthenticationProvider(){
        return new UserDetailsAuthenticationPasswordProvider();
    }

}
