package org.haozi.dto.mapper;

import org.haozi.dao.po.User;
import org.haozi.dto.entity.RoleResourcesDTO;
import org.haozi.dto.upms.UserDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/8 17:47
 */
@Mapper
public interface UserToUserDetailDTOMapper {
    UserToUserDetailDTOMapper INTANCE = Mappers.getMapper(UserToUserDetailDTOMapper.class);

    @Mapping(target = "userName",source = "user.username")
    @Mapping(target = "pwd",source = "user.pwd")
    @Mapping(target = "roleFlag",source = "roleResourcesDTO.roleFlag")
    UserDetailDTO convert(User user, RoleResourcesDTO roleResourcesDTO);
}
