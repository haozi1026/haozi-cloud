package org.haozi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.haozi.dao.po.Role;
import org.haozi.util.ResponseBuilder;
import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.SearchResult;
import org.haozi.dto.Response;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.haozi.dto.PageQuery;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author auto-generator
 * @since 2023-03-02
 */
@Controller
@RequestMapping("/role")
public class RoleController {
   @Resource
   private BeanSearcher beanSearcher;
    /**
     * 分页展示数据
     * @return 用户数据
     */
    @PostMapping("/page")
    public Response pagingQuery(@RequestBody PageQuery<Role> queryParam) {
        SearchResult<Role> search = beanSearcher.search(Role.class, queryParam.toParam());
        return ResponseBuilder.success(search);
    }
}
