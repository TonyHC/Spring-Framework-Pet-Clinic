package com.springframework.springpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping({"", "/", "index", "index.html"})
    public String index() {
        return "index";
    }

    /*
    * If we have an error.html file in resources/templates directory,
    * it'll automatically be picked up by the default Spring Boot's BasicErrorController.
     */
    @RequestMapping("oups")
    public String errorPage() {
        throw new RuntimeException(
                "Expected: controller used to showcase what " + "happens when an exception is thrown");
    }
}