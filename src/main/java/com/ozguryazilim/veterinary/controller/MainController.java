package com.ozguryazilim.veterinary.controller;


import com.ozguryazilim.veterinary.model.OwnerDto;
import com.ozguryazilim.veterinary.model.request.OwnerCreateRequest;
import com.ozguryazilim.veterinary.service.OwnerService;
import com.ozguryazilim.veterinary.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getLoginPage(Model model){
        model.addAttribute("ownerLogin",ownerService.getAllOwner());
        return "login";
    }

    @ModelAttribute("user")
    public OwnerCreateRequest ownerCreateRequest(){
        return new OwnerCreateRequest();
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        return "register";
    }
    @GetMapping("/save")
    public String getRegisterSavePage(Model model){
        return "register";
    }

    @PostMapping("/register")
    public String registerOwnerAccount(@ModelAttribute("user") OwnerCreateRequest ownerCreateRequest){
        ownerService.createOwner(ownerCreateRequest);
        return "redirect:/owner/register?success";
    }

    @PostMapping("/save")
    public String saveOwnerAccount(@ModelAttribute("user") OwnerCreateRequest ownerCreateRequest){
        ownerService.save(ownerCreateRequest);
        return "redirect:/owner/register?success";
    }





}
