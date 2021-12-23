package com.ozguryazilim.veterinary.service;


import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.exception.OwnerNotFoundException;
import com.ozguryazilim.veterinary.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> getAllOwner(){
        return ownerRepository.findAll();
    }

    public Owner getOwnerById(Long id){
        return ownerRepository.findById(id)
                .orElseThrow(()->new OwnerNotFoundException("owner not found id:"+id));
    }

    public Owner createOwner(Owner owner){
        return ownerRepository.save(owner);
    }

    public Owner updateOwnerById(Owner owner,Long id){
        Owner existingOwner = getOwnerById(id);
        existingOwner.setNameSurname(owner.getNameSurname());
        return ownerRepository.save(existingOwner);
    }

    public String deleteOwnerById(Long id){
        Owner owner = getOwnerById(id);
        ownerRepository.delete(owner);
        return "owner deleted id:"+id;
    }

}
