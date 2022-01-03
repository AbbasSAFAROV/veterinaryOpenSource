package com.ozguryazilim.veterinary.service;

import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.model.request.OwnerCreateRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Owner save(OwnerCreateRequest ownerCreateRequest);

}
