package org.haozi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/2 10:28
 */
@SpringBootApplication
@EnableDiscoveryClient
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class AuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }
}
