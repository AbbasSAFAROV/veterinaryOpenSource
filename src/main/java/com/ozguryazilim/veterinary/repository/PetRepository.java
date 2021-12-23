package com.ozguryazilim.veterinary.repository;

import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet,Long> {

    Pet findByName(String name);
    List<Pet> findByOwner(Owner owner);

}
