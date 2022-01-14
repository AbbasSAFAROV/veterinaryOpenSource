package com.ozguryazilim.veterinary.controller;


import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.entity.Pet;
import com.ozguryazilim.veterinary.model.request.PetCreateRequest;
import com.ozguryazilim.veterinary.service.OwnerService;
import com.ozguryazilim.veterinary.service.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetsController {

    private final PetService petService;
    private final OwnerService ownerService;
    private final ModelMapper modelMapper;

    public PetsController(PetService petService, OwnerService ownerService, ModelMapper modelMapper) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("pet")
    public PetCreateRequest pet(){
        return new PetCreateRequest();
    }

    @GetMapping("/pet/add")
    public String getPetAddPage(){
        return "addPet";
    }

    @PostMapping("/pet/add")
    public String getPetAddPage(@ModelAttribute("pet") PetCreateRequest pet){
        Owner currentOwner = ownerService.getCurrentUser();
        pet.setOwnerId(currentOwner.getId());
        petService.createPet(pet);

        return "redirect:/owner?success";
    }

    @GetMapping("/pet/update/{id}")
    public String getPetUpdatePage(@PathVariable("id") Long id, Model model){
        return "updatePet";
    }



}
