package org.haozi.service;

import lombok.NonNull;
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

    /**
     * 新增数据
     * @param user 新增数据信息
     */
    void add(User user);

    /**
     * 删
     * @param deleteDTO 删除参数，要描述删除主体信息（默认为主键）
     */
    void delete(@NonNull User deleteDTO);

    /**
     * 用户详情（用于 security认证）
     * @param userName 用户名
     * @return
     */
    UserDetailDTO userDetail(String userName);
}
