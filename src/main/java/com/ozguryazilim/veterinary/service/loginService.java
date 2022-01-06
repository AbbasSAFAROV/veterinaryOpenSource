package com.ozguryazilim.veterinary.service;

import com.ozguryazilim.veterinary.entity.Owner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class loginService implements UserDetails {

    Owner owner;

    public loginService(Owner owner) {
        this.owner = owner;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("USER_ROLE");
    }

    @Override
    public String getPassword() {
        return owner.getPassword();
    }

    @Override
    public String getUsername() {
        return owner.getEmail();
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
}
