package com.ozguryazilim.veterinary.controller;

import com.ozguryazilim.veterinary.service.OwnerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final OwnerService ownerService;
    private final ModelMapper modelMapper;

    public IndexController(OwnerService ownerService, ModelMapper modelMapper) {
        this.ownerService = ownerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String getIndexPage(Model model){
        model.addAttribute("owners",ownerService.getAllOwner());
        return "index";
    }

    @GetMapping("/admin/dashboard")
    public String getDashboardPage(Model model){
        model.addAttribute("owners",ownerService.getAllOwner());
        return "dashboard";
    }
}
