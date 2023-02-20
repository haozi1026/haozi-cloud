package org.haozi;

import cn.hutool.extra.spring.SpringUtil;
import org.haozi.event.DingTalkExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/20 17:36
 */
@Configuration
public class ExceptionConfig {

    @Bean
    @ConditionalOnProperty(DING_TAKK_TOKEN)
    public DingTalkExceptionHandler dingTalkExceptionHandler(){
        String accessToken = SpringUtil.getProperty(DING_TAKK_TOKEN);
        return new DingTalkExceptionHandler(accessToken);
    }

    private final String DING_TAKK_TOKEN = "system.errorNotice.dingTalk.access_token";
}
