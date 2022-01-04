package com.ozguryazilim.veterinary.controller;


import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.model.request.OwnerCreateRequest;
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

    @GetMapping("/index")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("ownerLogin",ownerService.getAllOwner());
        return "login";
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
        return "redirect:/owner/register?success";
    }

    @GetMapping("/delete/{id}")
    public String deleteOwnerById(@PathVariable("id") Long id, Model model){
        model.addAttribute("id",id);
        return "update";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable("id") Long id, Model model){
        model.addAttribute("id",id);
        return "update";
    }




    /**
    @GetMapping("/register")
    public String getRegisterPage(Model model){
        return "register";
    }

    @PostMapping("/register")
    public String registerOwnerAccount(@ModelAttribute("user") OwnerCreateRequest ownerCreateRequest){
        ownerService.createOwner(ownerCreateRequest);
        return "redirect:/owner/register?success";
    }
    **/
}
