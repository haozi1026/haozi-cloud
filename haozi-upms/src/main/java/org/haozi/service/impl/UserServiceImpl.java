package org.haozi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.NonNull;
import org.haozi.dao.po.User;
import org.haozi.dao.mapper.UserMapper;
import org.haozi.dto.entity.RoleResourcesDTO;
import org.haozi.dto.mapper.UserToUserDetailDTOMapper;
import org.haozi.dto.upms.UserDetailDTO;
import org.haozi.exception.ParamEmptyException;
import org.haozi.service.IResoucesService;
import org.haozi.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;


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
    public void add(User user) {

        // TODO: 2023/3/2 务必按照实际业务对入参做校验与处理
        this.baseMapper.insert(user);
    }

    @Override
    public void delete(@NonNull User deleteDTO) {

        Optional.ofNullable(deleteDTO).orElseThrow(() -> {
            String errorMsg = StrUtil.format("删除时传参错误,类名:",this.getClass());
            return new ParamEmptyException(errorMsg,"deleteDTO");
        });

        Optional.ofNullable(deleteDTO.getId()).orElseThrow(() -> {
            String errorMsg = StrUtil.format("删除时传参错误,类名:",this.getClass());
            return new ParamEmptyException(errorMsg,"id");
        });

        this.baseMapper.deleteById(deleteDTO);
    }

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
