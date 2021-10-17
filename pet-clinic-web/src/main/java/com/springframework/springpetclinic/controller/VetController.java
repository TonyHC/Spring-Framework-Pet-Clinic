package com.springframework.springpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vets")
public class VetController {
    @RequestMapping({"", "/", "/list", "/list.html"})
    public String listVets() {
        return "vets/list";
    }
}