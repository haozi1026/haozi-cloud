package org.haozi.service.impl;

import org.haozi.dao.po.Role;
import org.haozi.dao.mapper.RoleMapper;
    import org.haozi.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.Optional;
import cn.hutool.core.util.StrUtil;
import org.haozi.exception.ParamEmptyException;
import java.util.Optional;
import lombok.NonNull;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto-generator
 * @since 2023-03-02
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public void add(Role addDTO) {
        // TODO: 2023-03-02 务必按照实际业务对入参做校验与处理
        this.baseMapper.insert(addDTO);
    }

    @Override
    public void delete(Role deleteDTO) {

        Optional.ofNullable(deleteDTO).orElseThrow(() -> {
            String errorMsg = StrUtil.format("删除时传参错误,类名:",this.getClass());
            return new ParamEmptyException(errorMsg,"deleteDTO");
        });
         Optional.ofNullable(deleteDTO.getRoleId()).orElseThrow(() -> {
             String errorMsg = StrUtil.format("删除时传参错误,类名:",this.getClass());
             return new ParamEmptyException(errorMsg,"RoleId)");
         });

        // TODO: 2023-03-02 务必按照实际业务对入参做校验与处理
        this.baseMapper.deleteById(deleteDTO);
    }

    @Override
    public void update(Role  updateDTO) {
        Optional.ofNullable(updateDTO).orElseThrow(() -> {
            String errorMsg = StrUtil.format("修改时传参错误,类名:",this.getClass());
            return new ParamEmptyException(errorMsg,"updateDTO");
        });

        Optional.ofNullable(updateDTO.getRoleId()).orElseThrow(() -> {
            String errorMsg = StrUtil.format("修改时传参错误,类名:",this.getClass());
            return new ParamEmptyException(errorMsg,"RoleId)");
        });

        // TODO: 2023-03-02 务必按照实际业务对入参做校验与处理
        this.baseMapper.updateById(updateDTO);
    }
}
