package org.haozi;

import feign.RequestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
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

}
