package org.haozi.config;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.haozi.auth.HaoziOAuth2AuthorizationEndpointConfigurer;
import org.haozi.auth.authentication.password.HaoZiUserDetailsAuthenticationProvider;
import org.haozi.auth.endpoint.provider.AuthProvider;
import org.haozi.auth.endpoint.provider.PassworderProvider;
import org.haozi.dto.Response;
import org.haozi.util.ResponseBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

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
        return new HaoZiUserDetailsAuthenticationProvider();
    }
    //@Bean


}
