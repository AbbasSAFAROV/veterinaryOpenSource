package com.ozguryazilim.veterinary.service;


import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.entity.Pet;
import com.ozguryazilim.veterinary.exception.OwnerNotFoundException;
import com.ozguryazilim.veterinary.model.OwnerDto;
import com.ozguryazilim.veterinary.model.request.OwnerCreateRequest;
import com.ozguryazilim.veterinary.repository.OwnerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final ModelMapper modelMapper;

    public OwnerService(OwnerRepository ownerRepository, ModelMapper modelMapper) {
        this.ownerRepository = ownerRepository;
        this.modelMapper = modelMapper;
    }

    public List<OwnerDto> getAllOwner(){
        return ownerRepository
                .findAll()
                .stream()
                .map(x->modelMapper.map(x,OwnerDto.class))
                .collect(Collectors.toList());
    }

    public OwnerDto getOwnerById(Long id){
        return modelMapper.map(findOwnerById(id),OwnerDto.class);
    }

    public OwnerDto createOwner(OwnerCreateRequest owner){
        return modelMapper.map(ownerRepository.save(modelMapper.map(owner,Owner.class)),OwnerDto.class);
    }

    public OwnerDto updateOwnerById(OwnerCreateRequest owner,Long id){
        Owner existingOwner = findOwnerById(id);
        existingOwner.setNameSurname(owner.getNameSurname());
        return modelMapper.map(ownerRepository.save(existingOwner),OwnerDto.class);
    }

    public String deleteOwnerById(Long id){
        Owner owner = findOwnerById(id);
        ownerRepository.delete(owner);
        return "owner deleted id:"+id;
    }

    public String registerPet(Owner owner,List<Pet> pet){

        Owner owner1 = ownerRepository.findById(owner.getId()).get();
        owner1.setPets(pet);
        return "pet added!";
    }

    public OwnerDto findByName(String name){
        return modelMapper.map(ownerRepository.findByNameSurname(name),OwnerDto.class);
    }

    public Owner findOwnerById(Long id){
        return ownerRepository.findById(id).orElseThrow(()->new OwnerNotFoundException("Owner Not Found With This Id: "+id));
    }

}
