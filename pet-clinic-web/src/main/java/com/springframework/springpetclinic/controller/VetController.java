package com.springframework.springpetclinic.controller;

import com.springframework.springpetclinic.model.Vet;
import com.springframework.springpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"", "/vets", "/vets/list", "/vets/list.html", "/vets.html"})
    public String listVets(Model model) {
        Set<Vet> vets = vetService.findAll();

        model.addAttribute("vets", vets);

        return "vets/list";
    }
}