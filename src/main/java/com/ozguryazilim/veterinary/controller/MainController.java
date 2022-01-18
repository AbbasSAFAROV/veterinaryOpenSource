package com.ozguryazilim.veterinary.controller;


import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.model.PetDto;
import com.ozguryazilim.veterinary.service.AuthService;
import com.ozguryazilim.veterinary.service.OwnerService;
import com.ozguryazilim.veterinary.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class MainController {

    private final OwnerService ownerService;
    private final PetService petService;
    private final AuthService authService;
    private final ModelMapper modelMapper;

    public MainController(OwnerService ownerService, PetService petService, AuthService authService, ModelMapper modelMapper) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("user")
    public Owner owner(){
        return new Owner();
    }

    @GetMapping("/owner")
    public String ownerDetails(Model model){

        Owner currentUser = authService.getCurrentUser();

        if(currentUser!=null){
            Owner owner = ownerService.getOwnerByEmail(currentUser.getEmail());
            List<PetDto> petList = petService.getPetsByOwnerId(owner.getId());
            log.getName();
            Owner owner1 = ownerService.findOwnerById(owner.getId());
            model.addAttribute("pets",petList);
            model.addAttribute("user",owner1);
            return "owner";
        }
        return "redirect:/owner/login";
    }


    @GetMapping("/owner/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/owner/save")
    public String getRegisterSavePage(Model model){
        return "register";
    }

    @PostMapping("/owner/save")
    public String saveOwnerAccount(@ModelAttribute("user") Owner owner){
        ownerService.createOwner(owner);
        //String redirect = "redirect:/owner/register?success";
        return "redirect:/owner/login?success";
    }

    @GetMapping("/owner/delete/{id}")
    public String deleteOwnerById(@PathVariable("id") Long id){
        ownerService.deleteOwnerById(id);
        return "redirect:/";
    }

    @GetMapping("/owner/update/{id}")
    public String getUpdatePage(@PathVariable("id") Long id, Model model){
        Owner user = ownerService.findOwnerById(id);
        model.addAttribute("user",user);
        return "update";
    }

    @PostMapping("/owner/edit/{id}")
    public String updateOwner(@PathVariable("id") Long id,@ModelAttribute("user") Owner owner){
        ownerService.updateOwnerById(owner,id);
        //ownerService.updateOwner(owner);
        return "redirect:/";
    }

    @GetMapping("/owner/search")
    public String searchOwner(@RequestParam("search") String search,Model model){
        Owner owner = modelMapper.map(ownerService.findByName(search),Owner.class);
        model.addAttribute("user",owner);
        return "detail";
    }

}
