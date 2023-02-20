package org.haozi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/9 10:59
 */
@Configuration
@EnableWebSecurity
public class UpmsSecurityConfig {

    @Bean
    public SecurityFilterChain upmsSecurityChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests(auth ->{
                            auth
                                    .requestMatchers("/user/userDetail")
                                    .permitAll();
                            auth
                                    .anyRequest().authenticated();
                        })

                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        return httpSecurity.build();
    }
}
