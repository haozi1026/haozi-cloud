package org.haozi.controller;

import cn.hutool.core.util.StrUtil;
import org.haozi.dto.Response;
import org.haozi.dto.upms.UserDetailDTO;
import org.haozi.exception.ParamEmptyException;
import org.haozi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author auto-generator
 * @since 2023-02-02
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 根据用户名查用户详情(用于security)
     * @param userDetailDTO
     * @return
     */
    @PostMapping("/userDetail")
    public Response userDetailByUsername(@RequestBody UserDetailDTO userDetailDTO){

        if(StrUtil.isBlank(userDetailDTO.getUserName())){
            throw new ParamEmptyException("根据用户名查询用户详情","userName");
        }
        userService.userDetail(userDetailDTO.getUserName());
        return null;
    }

}
