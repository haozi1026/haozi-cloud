package org.haozi.core.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/20 16:28
 */
public class InternalInterceptor implements RequestInterceptor {
    private String headerKey;
    private String headerVal;
    public InternalInterceptor(String headerKey,String headerVal){
        this.headerKey = headerKey;
        this.headerVal = headerVal;
    }
    @Override
    public void apply(RequestTemplate template) {
        template.header(headerKey, headerVal);
    }


}
