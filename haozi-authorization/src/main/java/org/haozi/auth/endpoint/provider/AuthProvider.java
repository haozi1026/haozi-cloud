package org.haozi.auth.endpoint.provider;

import jakarta.servlet.http.HttpServletRequest;
import org.haozi.auth.token.HaoZiToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.MultiValueMap;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/6 19:49
 */
public interface AuthProvider {

    /**
     * 根据granType匹配
     * @param granType
     * @return
     */
    boolean isSupport(String granType);

    /**
     * 转换
     * @param request
     * @return
     */
    HaoZiToken convert(HttpServletRequest request, MultiValueMap<String, String> parameters);

    /**
     * 认证
     * @param token
     * @return
     */
    Authentication auth(HaoZiToken token);
}
