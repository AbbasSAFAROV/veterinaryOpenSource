package com.ozguryazilim.veterinary.repository;

import com.ozguryazilim.veterinary.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OwnerRepository extends JpaRepository<Owner,Long> {

    Owner findByNameSurnameContaining(String name);
    Owner findByEmail(String email);

}
