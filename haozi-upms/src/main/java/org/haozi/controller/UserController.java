package org.haozi.controller;

import cn.hutool.core.util.StrUtil;
import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.SearchResult;
import jakarta.annotation.Resource;
import org.haozi.dao.po.User;
import org.haozi.dto.PageQuery;
import org.haozi.dto.Response;
import org.haozi.dto.upms.UserDetailDTO;
import org.haozi.exception.ParamEmptyException;
import org.haozi.security.anon.Internal;
import org.haozi.service.IUserService;
import org.haozi.util.ResponseBuilder;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author auto-generator
 * @since 2023-02-02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    IUserService userService;
    @Resource
    BeanSearcher beanSearcher;

    /**
     * 根据用户名查用户详情(用于security)
     * @param userDetailDTO 用户数据查询参数
     * @return 用户详情数据
     */
    @PostMapping("/userDetail")
    @Internal
    public Response<UserDetailDTO> userDetailByUsername(@RequestBody UserDetailDTO userDetailDTO){

        if(StrUtil.isBlank(userDetailDTO.getUserName())){
            throw new ParamEmptyException("根据用户名查询用户详情","userName");
        }
        return ResponseBuilder.success(userService.userDetail(userDetailDTO.getUserName()));
    }

    /**
     * 分页展示用户数据
     * @return 用户数据
     */
    @PostMapping("/page")
    public Response userPaging(@RequestBody PageQuery<User> user){
        SearchResult<User> search = beanSearcher.search(User.class, user.toParam());
        return ResponseBuilder.success(search);
    }

}
