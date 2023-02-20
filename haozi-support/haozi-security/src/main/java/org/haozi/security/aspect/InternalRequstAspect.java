package org.haozi.security.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.haozi.constants.InternalConstant;
import org.haozi.exception.AccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 服务间内部调用校验
 * @author zyh
 * @version 1.0
 * @date 2023/2/18 11:40
 */
@Aspect
@Component
public class InternalRequstAspect {


    /**
     * 标记为系统内部调用接口
     */
    @Pointcut("@annotation(org.haozi.security.anon.Internal)")
    public void pointCutInternal(){

    }

    /**
     * 校验内部交互接口的访问是否合法
     */
    @Before("org.haozi.security.aspect.InternalRequstAspect.pointCutInternal()")
    public void doAccessCheck(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String internalHeaderSercet = request.getHeader(InternalConstant.HEADER_INTERNAL_SERCET);

        if(StrUtil.isBlank(internalHeaderSercet)){
            throw new AccessException("系统内部接口，禁止访问");
        }
        String secret = SpringUtil.getProperty(InternalConstant.SERCET_KEY);
        if(StrUtil.equals(secret,internalHeaderSercet) == false){
            throw new AccessException("系统内部接口,密钥错误,禁止访问");
        }
    }

}
