package org.haozi.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import jakarta.annotation.Resource;
import org.haozi.dao.po.User;
import org.haozi.dto.Response;
import org.haozi.dto.upms.UserDetailDTO;
import org.haozi.exception.ParamEmptyException;
import org.haozi.security.Internal;
import org.haozi.service.IUserService;
import org.haozi.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
     * @param userDetailDTO
     * @return
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
     * @return
     */
    @PostMapping("/page")
    public Response userPaging(@RequestBody User user){

        SearchResult<User> search = beanSearcher.search(User.class, BeanUtil.beanToMap(user));
        return ResponseBuilder.success(search);
    }

}
