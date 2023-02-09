package org.haozi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.haozi.dao.po.User;
import org.haozi.dao.mapper.UserMapper;
import org.haozi.dto.upms.UserDetailDTO;
import org.haozi.exception.ParamEmptyException;
import org.haozi.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        return null;
    }
}
