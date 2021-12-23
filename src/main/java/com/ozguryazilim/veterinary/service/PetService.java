package com.ozguryazilim.veterinary.service;


import com.ozguryazilim.veterinary.entity.Pet;
import com.ozguryazilim.veterinary.exception.PetNotFoundException;
import com.ozguryazilim.veterinary.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    // There are 3 Type Dependecy Injection ->1-Field Injection 2-Setter Injection 3-Constructure Injection
    private final PetRepository petRepository; // immutable -> final - thread safety

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

    public Pet getPetById(Long id){
        return petRepository.findById(id)
                .orElseThrow(()->new PetNotFoundException("pet not found with this id:"+id));
    }

    public Pet createPet(Pet pet){
        return petRepository.save(pet);
    }

    public Pet updatePetById(Pet pet, Long id){

        Pet existingPet = getPetById(id);
        existingPet.setName(pet.getName());
        existingPet.setDescription(pet.getDescription());
        existingPet.setType(pet.getType());
        existingPet.setGenus(pet.getGenus());

        return petRepository.save(existingPet);
    }

    public String deletePetById(Long id){
        Pet pet = getPetById(id);
        petRepository.delete(pet);
        return "pet deleted with this id :"+id;
    }



}
