package com.ozguryazilim.veterinary.controller;


import com.ozguryazilim.veterinary.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner")
public class MainController {

    private final OwnerService ownerService;

    public MainController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/hello")
    public String getAllOwners(Model model){
        model.addAttribute("owners",ownerService.getAllOwner());
        return "owner";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("ownerLogin",ownerService.getAllOwner());
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("ownerLogin",ownerService.getAllOwner());
        return "register";
    }

}
