package com.ozguryazilim.veterinary.config;

import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.entity.Pet;
import com.ozguryazilim.veterinary.entity.Role;
import com.ozguryazilim.veterinary.entity.UserRole;
import com.ozguryazilim.veterinary.repository.OwnerRepository;
import com.ozguryazilim.veterinary.repository.PetRepository;
import com.ozguryazilim.veterinary.service.OwnerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerRepository repository;
    private final OwnerService service;
    private final PetRepository petRepository;

    public DataLoader(OwnerRepository repository, OwnerService service, PetRepository petRepository) {
        this.repository = repository;
        this.service = service;
        this.petRepository = petRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner = new Owner("abbas","izmir","+905459149870","abbas@gmail.com","abbas","123456",Arrays.asList(new Role("USER")));
        Owner owner2 = new Owner("admin","izmir","+905459149870","admin@gmail.com","admin","123456",Arrays.asList(new Role("ADMIN")));
        Owner owner3 = new Owner("qorya","izmir","+905459149870","qorya@gmail.com","qorya","123456",Arrays.asList(new Role("USER")));




        Pet newPet= new Pet("karabash","eğitimli","12","Kangal","sivas kangal",owner);
        Pet newPet1= new Pet("karabash1","eğitimli","12","Kangal","sivas kangal",owner2);
        Pet newPet2= new Pet("karabash2","eğitimli","12","Kangal","sivas kangal",owner3);

        service.createOwner(owner);
        service.createOwner(owner2);
        service.createOwner(owner3);

        petRepository.save(newPet);
        petRepository.save(newPet1);
        //petRepository.save(newPet2);


    }
}
