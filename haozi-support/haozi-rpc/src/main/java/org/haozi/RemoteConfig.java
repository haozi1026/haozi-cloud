package org.haozi;

import cn.hutool.extra.spring.SpringUtil;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;
import org.haozi.constants.InternalConstant;
import org.haozi.core.interceptor.InternalInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/9 14:00
 */
@EnableFeignClients
@Configuration
public class RemoteConfig {

    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public InternalInterceptor requestInternalInterceptor() {
        String secrcet = SpringUtil.getProperty(InternalConstant.SERCET_KEY);
        return new InternalInterceptor(InternalConstant.HEADER_INTERNAL_SERCET,secrcet);
    }


}
