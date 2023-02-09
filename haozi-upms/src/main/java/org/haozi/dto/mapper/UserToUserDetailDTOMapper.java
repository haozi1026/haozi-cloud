package org.haozi.dto.mapper;

import org.haozi.dao.po.User;
import org.haozi.dto.upms.UserDetailDTO;
import org.mapstruct.Mapper;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/8 17:47
 */
@Mapper
public interface UserToUserDetailDTOMapper {
    UserDetailDTO convert(User user);
}
