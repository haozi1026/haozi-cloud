package org.haozi.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.haozi.dao.mapper.RoleResourcesMapper;
import org.haozi.dao.mapper.UserRoleMapper;
import org.haozi.dao.po.Resouces;
import org.haozi.dao.mapper.ResoucesMapper;
import org.haozi.dao.po.RoleResources;
import org.haozi.dao.po.UserRole;
import org.haozi.service.IResoucesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto-generator
 * @since 2023-02-09
 */
@Service
public class ResoucesServiceImpl extends ServiceImpl<ResoucesMapper, Resouces> implements IResoucesService {
   @Resource
   private UserRoleMapper userRoleMapper;
   @Resource
   private RoleResourcesMapper roleResourcesMapper;

    @Override
    public List<Resouces> findResourceByUserId(Long userId) {

        LambdaQueryWrapper<UserRole> userRoleQuery = new LambdaQueryWrapper();
        userRoleQuery.eq(UserRole::getUserId,userId);

        List<UserRole> userRoles = userRoleMapper.selectList(userRoleQuery);
        if(CollUtil.isEmpty(userRoles)){
            return new ArrayList<>();
        }

        LambdaQueryWrapper<RoleResources> roleResourcesLambdaQueryWrapper
                = new LambdaQueryWrapper<>();

        List<Long> roleIds = userRoles.stream().map(userRole -> userRole.getRoleId()).collect(Collectors.toList());

        roleResourcesLambdaQueryWrapper
                .in(RoleResources::getRoleId,roleIds);

        List<RoleResources> roleResources = roleResourcesMapper.selectList(roleResourcesLambdaQueryWrapper);

        if(CollUtil.isEmpty(roleResources)){
            return null;
        }
        List<Long> resourceIds = roleResources.stream().map(roleResource -> roleResource.getResourceId()).collect(Collectors.toList());

        List<Resouces> resouces = this.baseMapper.selectBatchIds(resourceIds);

        return resouces;
    }
}
