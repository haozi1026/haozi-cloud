package org.haozi.dto.entity;


import lombok.Data;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/9 10:37
 */
@Data
public class RoleResourcesDTO {

    /**
     * 角色标识
     */
    private List<String> roleFlag;
    /**
     * 资源标识
     */
    private List<String> respourcesFlag;

}
