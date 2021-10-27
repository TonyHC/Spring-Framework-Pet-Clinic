package com.springframework.springpetclinic.controller;

import com.springframework.springpetclinic.model.Vet;
import com.springframework.springpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vets", "/vets/list", "/vets/vetList.html", "/vets.html"})
    public String listVets(Model model) {
        Set<Vet> vets = vetService.findAll();

        model.addAttribute("vets", vets);

        return "vets/vetList";
    }

    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetsJSON() {
        // Behind the scenes, Spring uses Jackson to convert the Vet Java POJO into JSON
        // and display the JSON on /api/vets mapping
        return vetService.findAll();
    }
}