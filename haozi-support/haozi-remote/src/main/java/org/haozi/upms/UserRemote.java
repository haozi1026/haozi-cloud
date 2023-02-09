package org.haozi.upms;

import org.haozi.dto.Response;
import org.haozi.dto.upms.UserDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/9 14:08
 */
@FeignClient("haozi-upms")
public interface UserRemote {

    /**
     * 按用户名查用户详细信息
     * @param userDetailDTO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/user/userDetail", consumes = "application/json")
    Response<UserDetailDTO> userDetailByUsername(UserDetailDTO userDetailDTO);


}
