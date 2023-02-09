package org.haozi.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.haozi.dao.mapper.RoleMapper;
import org.haozi.dao.mapper.RoleResourcesMapper;
import org.haozi.dao.mapper.UserRoleMapper;
import org.haozi.dao.po.Resouces;
import org.haozi.dao.mapper.ResoucesMapper;
import org.haozi.dao.po.Role;
import org.haozi.dao.po.RoleResources;
import org.haozi.dao.po.UserRole;
import org.haozi.dto.entity.RoleResourcesDTO;
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
    @Resource
    private RoleMapper roleMapper;
    @Override
    public RoleResourcesDTO findResourceByUserId(Long userId) {

        LambdaQueryWrapper<UserRole> userRoleQuery = new LambdaQueryWrapper();
        userRoleQuery.eq(UserRole::getUserId,userId);

        List<UserRole> userRoles = userRoleMapper.selectList(userRoleQuery);
        if(CollUtil.isEmpty(userRoles)){
            return null;
        }

        LambdaQueryWrapper<RoleResources> roleResourcesLambdaQueryWrapper
                = new LambdaQueryWrapper<>();

        List<Long> roleIds = userRoles.stream().map(userRole -> userRole.getRoleId()).collect(Collectors.toList());

        roleResourcesLambdaQueryWrapper
                .in(RoleResources::getRoleId,roleIds);

        List<RoleResources> roleResources = roleResourcesMapper.selectList(roleResourcesLambdaQueryWrapper);

        LambdaQueryWrapper<Role> roleQuery = new LambdaQueryWrapper<Role>();

        List<Role> roles = roleMapper.selectList(roleQuery);
        if(CollUtil.isEmpty(roleResources)){
            return null;
        }
        List<Long> resourceIds = roleResources.stream().map(roleResource -> roleResource.getResourceId()).collect(Collectors.toList());
        List<Resouces> resouces = this.baseMapper.selectBatchIds(resourceIds);
        RoleResourcesDTO roleResourcesDTO = new RoleResourcesDTO();

        List<String> resourceFlag = resouces.stream().map(resource -> resource.getResourceFlag()).collect(Collectors.toList());
        List<String> roleFlag = roles.stream().map(role -> role.getRoleFlag()).collect(Collectors.toList());

        roleResourcesDTO.setRespourcesFlag(resourceFlag);
        roleResourcesDTO.setRoleFlag(roleFlag);

        return roleResourcesDTO;
    }
}
