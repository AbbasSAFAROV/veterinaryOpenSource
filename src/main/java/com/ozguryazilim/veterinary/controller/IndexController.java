package com.ozguryazilim.veterinary.controller;

import com.ozguryazilim.veterinary.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final UserService ownerService;
    private final ModelMapper modelMapper;

    public IndexController(UserService ownerService, ModelMapper modelMapper) {
        this.ownerService = ownerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String getIndexPage(Model model){
        model.addAttribute("owners",ownerService.getAllOwner());
        return "index";
    }

}
