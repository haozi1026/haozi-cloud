package org.haozi.service;

import org.haozi.dao.po.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.NonNull;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto-generator
 * @since 2023-03-02
 */
public interface IRoleService extends IService<Role> {

        /**
         * 新增数据
         * @param addDTO 新增数据信息
        */
        void add(Role addDTO);

        /**
         * 删
         * @param deleteDTO 删除参数，要描述删除主体信息（默认为主键）
         */
        void delete(Role deleteDTO);
        /**
          * 改
          * @param updateDTO 修改参数，要描述修改主体信息（默认为主键）与修改字段
          */
        void update(Role updateDTO);
}
