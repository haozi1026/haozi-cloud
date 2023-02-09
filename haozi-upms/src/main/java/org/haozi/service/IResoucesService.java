package org.haozi.service;

import org.haozi.dao.po.Resouces;
import com.baomidou.mybatisplus.extension.service.IService;
import org.haozi.dto.entity.RoleResourcesDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto-generator
 * @since 2023-02-09
 */
public interface IResoucesService extends IService<Resouces> {
    /**
     * 根据用户ID，取出该用户下可访问资源
     * @param userId
     * @return
     */
    RoleResourcesDTO findResourceByUserId(Long userId);


}
