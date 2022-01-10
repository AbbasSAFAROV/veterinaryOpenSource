package com.ozguryazilim.veterinary.service;

import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.model.OwnerDto;
import com.ozguryazilim.veterinary.model.request.OwnerCreateRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    Owner save(Owner owner);
    List<OwnerDto> getAllOwner();
    OwnerDto getOwnerById(Long id);
    OwnerDto createOwner(OwnerCreateRequest owner);
    OwnerDto updateOwnerById(Owner owner, Long id);
    String deleteOwnerById(Long id);
    OwnerDto findByName(String name);
    Owner activateAdmin(Long id);
    Owner findByUsername(String name);
    Owner updateOwner(Owner owner);
}
