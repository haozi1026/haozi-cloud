package org.haozi.service;

import org.haozi.dao.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.haozi.dto.upms.UserDetailDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto-generator
 * @since 2023-02-02
 */
public interface IUserService extends IService<User> {

    UserDetailDTO userDetail(String userName);
}
