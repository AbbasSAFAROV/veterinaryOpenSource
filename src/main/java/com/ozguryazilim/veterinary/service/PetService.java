package com.ozguryazilim.veterinary.service;


import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.entity.Pet;
import com.ozguryazilim.veterinary.exception.PetNotFoundException;
import com.ozguryazilim.veterinary.model.PetDto;
import com.ozguryazilim.veterinary.model.request.PetCreateRequest;
import com.ozguryazilim.veterinary.repository.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    // There are 3 Type Dependecy Injection ->1-Field Injection 2-Setter Injection 3-Constructure Injection
    private final PetRepository petRepository; // immutable -> final - thread safety
    private final ModelMapper modelMapper;
    private final OwnerService service;

    public PetService(PetRepository petRepository, ModelMapper modelMapper, OwnerService service) {
        this.petRepository = petRepository;
        this.modelMapper = modelMapper;
        this.service = service;
    }

    public List<PetDto> getAllPets(){
        return petRepository.findAll().stream().map(x->modelMapper.map(x,PetDto.class)).collect(Collectors.toList());
    }

    public PetDto getPetById(Long id){
        return modelMapper.map(findById(id),PetDto.class);
    }

    public PetDto createPet(PetCreateRequest pet){
        Owner owner = service.findOwnerById(pet.getOwnerId());
        Pet newPet= new Pet(pet.getName(),pet.getDescription(),pet.getAge(),pet.getType(),pet.getGenus(),owner);
        return modelMapper.map(petRepository.save(newPet),PetDto.class);
    }

    public PetDto updatePetById(PetCreateRequest pet, Long id){

        Pet existingPet = findById(id);
        existingPet.setName(pet.getName());
        existingPet.setDescription(pet.getDescription());
        existingPet.setType(pet.getType());
        existingPet.setGenus(pet.getGenus());

        return modelMapper.map(petRepository.save(existingPet),PetDto.class);
    }

    public String deletePetById(Long id){
        Pet pet = findById(id);
        petRepository.delete(pet);
        return "pet deleted with this id :"+id;
    }

    public Pet findById(Long id){
        return petRepository.findById(id).orElseThrow(()->new PetNotFoundException("pet not found with this id:"+id));
    }

    public PetDto findByName(String name){
        return modelMapper.map(petRepository.findByName(name),PetDto.class);
    }


}
