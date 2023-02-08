package org.haozi.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/8 14:02
 */
@RequestMapping("/test")
@Controller
public class TestController {

    @RequestMapping("/test")
    private void test(){
        SecurityContext context = SecurityContextHolder.getContext();

    }
}
