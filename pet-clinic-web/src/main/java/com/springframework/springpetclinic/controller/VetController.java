package com.springframework.springpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {
    @RequestMapping({"/vets", "/vets/list", "/vets/list.html"})
    public String listVets() {
        return "vets/list";
    }
}