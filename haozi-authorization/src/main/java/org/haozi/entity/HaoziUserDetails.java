package org.haozi.entity;

import cn.hutool.core.collection.CollUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/9 15:45
 */
public class HaoziUserDetails implements UserDetails {
    private String username;
    private List<String> resourceFlagList;
    private String password;

    public HaoziUserDetails(String userName, String password,List<String> resourceFlag) {
        this.username = userName;
        this.password = password;
        this.resourceFlagList = resourceFlag;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (CollUtil.isEmpty(resourceFlagList)) {
            return null;
        }
        List<HaoziGrantedAuthority> HaoziGrantedAuthority
                = resourceFlagList.stream().map(resource -> new HaoziGrantedAuthority(resource)).collect(Collectors.toList());
        return HaoziGrantedAuthority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    class HaoziGrantedAuthority implements GrantedAuthority {
        private String authority;

        public HaoziGrantedAuthority(String resourceFlag) {
            this.authority = resourceFlag;
        }

        @Override
        public String getAuthority() {
            return authority;
        }
    }
}
