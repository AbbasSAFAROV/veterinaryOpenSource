package com.ozguryazilim.veterinary.controller;


import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.entity.Pet;
import com.ozguryazilim.veterinary.model.OwnerDto;
import com.ozguryazilim.veterinary.model.PetDto;
import com.ozguryazilim.veterinary.service.PetService;
import com.ozguryazilim.veterinary.service.UserService;
import com.ozguryazilim.veterinary.service.loginService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class MainController {

    private final UserService ownerService;
    private final PetService petService;
    private final ModelMapper modelMapper;

    public MainController(UserService ownerService, PetService petService, ModelMapper modelMapper) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/owner")
    public String ownerDetails(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info(auth.getName());
        log.info(auth.getPrincipal().toString());
        log.info(auth.getAuthorities().toString());

        if(!(auth instanceof AnonymousAuthenticationToken)){
            loginService name = (loginService) auth.getPrincipal();
            Owner owner = ownerService.findByUsername(name.getUsername());
            List<PetDto> petList = petService.getPetsByOwnerId(owner.getId());
            OwnerDto owner1 = ownerService.getOwnerById(owner.getId());
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

    @ModelAttribute("user")
    public Owner owner(){
        return new Owner();
    }

    @GetMapping("/owner/save")
    public String getRegisterSavePage(Model model){
        return "register";
    }

    @PostMapping("/owner/save")
    public String saveOwnerAccount(@ModelAttribute("user") Owner owner){
        ownerService.save(owner);
        //String redirect = "redirect:/owner/register?success";
        return "redirect:/owner/login?success";
    }

    @GetMapping("/owner/delete/{id}")
    public String deleteOwnerById(@PathVariable("id") Long id){
        ownerService.deleteOwnerById(id);
        return "redirect:/owner";
    }

    @GetMapping("/owner/update/{id}")
    public String getUpdatePage(@PathVariable("id") Long id, Model model){
        Owner user = modelMapper.map(ownerService.getOwnerById(id),Owner.class);
        model.addAttribute("user",user);
        return "update";
    }

    @PostMapping("/owner/edit/{id}")
    public String updateOwner(@PathVariable("id") Long id,@ModelAttribute("user") Owner owner){
        ownerService.updateOwnerById(owner,id);
        //ownerService.updateOwner(owner);
        return "redirect:/owner";
    }

    @GetMapping("/owner/search")
    public String searchOwner(@RequestParam("search") String search,Model model){
        Owner owner = modelMapper.map(ownerService.findByName(search),Owner.class);
        model.addAttribute("user",owner);
        return "detail";
    }

    @GetMapping("/owner/activate/admin/{id}")
    public String getActivateAdmin(@PathVariable("id") Long id){
        ownerService.activateAdmin(id);
        return "redirect:/owner";
    }

    @GetMapping("/admin/dashboard")
    public String getDashboardPage(Model model){
        model.addAttribute("owners",ownerService.getAllOwner());
        return "dashboard";
    }


}
