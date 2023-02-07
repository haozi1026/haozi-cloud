package org.haozi.util;

import cn.hutool.core.util.CharsetUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.haozi.constants.ResponseCode;
import org.haozi.dto.Response;

import java.io.IOException;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/7 14:27
 */
public class ResponseWriter {

    public static void write(String content, HttpServletResponse response){
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        try {
            response.getWriter().write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
