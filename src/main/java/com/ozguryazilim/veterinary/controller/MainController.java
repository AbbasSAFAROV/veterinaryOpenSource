package com.ozguryazilim.veterinary.controller;


import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owner")
public class MainController {

    private final UserService ownerService;

    public MainController(UserService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping()
    public String getAllOwners(Model model){
        model.addAttribute("owners",ownerService.getAllOwner());
        return "owner";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "redirect:/owner";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/owner";
    }

    @ModelAttribute("user")
    public Owner owner(){
        return new Owner();
    }

    @GetMapping("/save")
    public String getRegisterSavePage(Model model){
        return "register";
    }

    @PostMapping("/save")
    public String saveOwnerAccount(@ModelAttribute("user") Owner owner){
        ownerService.save(owner);
        String redirect = "redirect:/owner/register?success";
        return "redirect:/owner";
    }

    @GetMapping("/delete/{id}")
    public String deleteOwnerById(@PathVariable("id") Long id){
        ownerService.deleteOwnerById(id);
        return "redirect:/owner";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable("id") Long id, Model model){
        model.addAttribute("id",id);
        return "update";
    }

    @GetMapping("/activate/admin/{id}")
    public String getActivateAdmin(@PathVariable("id") Long id){
        ownerService.activateAdmin(id);
        return "redirect:/owner";
    }

}
