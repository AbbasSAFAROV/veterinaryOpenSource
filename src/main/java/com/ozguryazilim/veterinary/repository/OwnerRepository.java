package com.ozguryazilim.veterinary.repository;

import com.ozguryazilim.veterinary.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OwnerRepository extends JpaRepository<Owner,Long> {

    Owner findByNameSurname(String name);
    Owner findByEmail(String email);

}
