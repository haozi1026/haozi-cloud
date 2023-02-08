package org.haozi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/1 15:44
 */
@SpringBootApplication
@MapperScan("org.haozi.dao.mapper")
@Import(cn.hutool.extra.spring.SpringUtil.class)
@EnableDiscoveryClient
public class UpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpmsApplication.class, args);
    }


}
