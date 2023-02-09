package org.haozi.auth.authentication.password;

import cn.hutool.extra.spring.SpringUtil;
import org.haozi.constants.ResponseCode;
import org.haozi.dto.Response;
import org.haozi.dto.upms.UserDetailDTO;
import org.haozi.entity.HaoziUserDetails;
import org.haozi.exception.AuthException;
import org.haozi.exception.InternalException;
import org.haozi.upms.UserRemote;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/7 10:51
 */
public class UserDetailsAuthenticationPasswordProvider extends AbstractUserDetailsAuthenticationProvider {
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String presentedPassword = authentication.getCredentials().toString();
        boolean matches = SpringUtil.getBean(PasswordEncoder.class).matches(presentedPassword, userDetails.getPassword());
        if (!matches) {
            throw new AuthException("用户名或密码错误");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setUserName(username);

        Response<UserDetailDTO> userDetailDTOResponse = SpringUtil.getBean(UserRemote.class)
                .userDetailByUsername(userDetailDTO);
        if(userDetailDTOResponse.getCode()!= ResponseCode.SUCCESS.getCode()){
            throw new InternalException("调用upms查询用户详情失败");
        }
        UserDetailDTO userDetailDTORes = userDetailDTOResponse.getData();

        HaoziUserDetails haoziUserDetails
                = new HaoziUserDetails(userDetailDTORes.getUserName(),
                userDetailDTORes.getPwd(),
                userDetailDTORes.getResourceFlag());

        return haoziUserDetails;
    }
}
