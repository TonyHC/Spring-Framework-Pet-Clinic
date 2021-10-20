package com.springframework.springpetclinic.controller;

import com.springframework.springpetclinic.model.Owner;
import com.springframework.springpetclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/", "/list", "/list.html"})
    public String listOwners(Model model) {
        Set<Owner> owners = ownerService.findAll();

        model.addAttribute("owners", owners);

        return "owners/list";
    }

    @RequestMapping("/find")
    public String findOwners() {
        return "notImplemented";
    }
}