package org.haozi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.haozi.dao.po.User;
import org.haozi.dao.mapper.UserMapper;
import org.haozi.dto.entity.RoleResourcesDTO;
import org.haozi.dto.mapper.UserToUserDetailDTOMapper;
import org.haozi.dto.upms.UserDetailDTO;
import org.haozi.exception.ParamEmptyException;
import org.haozi.service.IResoucesService;
import org.haozi.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author auto-generator
 * @since 2023-02-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private IResoucesService resoucesService;

    @Override
    public UserDetailDTO userDetail(String userName) {
        if (StrUtil.isBlank(userName)) {
            throw new ParamEmptyException("根据用户名查询用户详情", "userName");
        }
        LambdaQueryWrapper<User>
                queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUsername, userName);
        User user = this.baseMapper.selectOne(queryWrapper);
        if (user == null) {
            return null;
        }
        RoleResourcesDTO resourceByUserDTO = resoucesService.findResourceByUserId(user.getId());

        UserDetailDTO userDetailDTO = UserToUserDetailDTOMapper.INTANCE.convert(user, resourceByUserDTO);
        return userDetailDTO;
    }
}
