package com.ozguryazilim.veterinary.service;

import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthService implements UserDetailsService {

    private final OwnerService service;

    public AuthService(OwnerService service) {
        this.service = service;
    }

    public Owner getCurrentUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        log.info(auth.getAuthorities().toString());

        if(!(auth instanceof AnonymousAuthenticationToken)){
            Object name = (Object) auth.getPrincipal();
            Owner owner = service.getOwnerByEmail(auth.getName());
            return owner;
        }
        return null;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Owner owner = service.getOwnerByEmail(email);
        if (owner == null)
            throw new UsernameNotFoundException("Invalid Username or password 01");
        return new User(owner.getEmail(),owner.getPassword(),mapRoles(owner.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoles(Collection<Role> roles){
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
