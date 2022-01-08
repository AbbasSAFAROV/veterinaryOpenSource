package com.ozguryazilim.veterinary.controller;


import com.ozguryazilim.veterinary.entity.Pet;
import com.ozguryazilim.veterinary.service.OwnerService;
import com.ozguryazilim.veterinary.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetsController {

    private final PetService petService;
    private final OwnerService ownerService;

    public PetsController(PetService petService, OwnerService ownerService) {
        this.petService = petService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("pet")
    public Pet pet(){
        return new Pet();
    }

    @GetMapping("/pet/add")
    public String getPetAddPage(){
        return "addPet";
    }

    @PostMapping("/pet/add")
    public String getPetAddPage(@ModelAttribute("pet") Pet pet){
        return "redirect:/owner?success";
    }


}
