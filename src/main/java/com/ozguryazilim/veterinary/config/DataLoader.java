package com.ozguryazilim.veterinary.config;

import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.entity.UserRole;
import com.ozguryazilim.veterinary.repository.OwnerRepository;
import com.ozguryazilim.veterinary.service.OwnerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerRepository repository;
    private final OwnerService service;

    public DataLoader(OwnerRepository repository, OwnerService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {


        Owner owner = new Owner("abbas","izmir","+905459149870","abbas@gmail.com","abbas","123456");
        Owner owner2 = new Owner("admin","izmir","+905459149870","admin@gmail.com","admin","123456");
        owner.setUserRole(UserRole.USER_ROLE);
        owner2.setUserRole(UserRole.USER_ROLE);

        service.save(owner);
        service.save(owner2);


    }
}
