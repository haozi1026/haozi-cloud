package org.haozi.dto.upms;

import lombok.Data;
import org.mapstruct.factory.Mappers;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/8 17:36
 */
@Data
public class UserDetailDTO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String pwd;

}
