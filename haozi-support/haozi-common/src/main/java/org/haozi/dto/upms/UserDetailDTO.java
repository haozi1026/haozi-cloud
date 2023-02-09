package org.haozi.dto.upms;

import lombok.Data;

import java.util.List;

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

    /**
     * 资源标识
     */
    private List<String> resourceFlag;

    /**
     * 角色标识
     */
    private List<String> roleFlag;

}
