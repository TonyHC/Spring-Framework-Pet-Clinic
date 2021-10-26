package com.springframework.springpetclinic.controller;

import com.springframework.springpetclinic.model.Owner;
import com.springframework.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String findOwnersForm(Model model) {
        Owner owner = new Owner();

        model.addAttribute("owner", owner);

        return "owners/findOwners";
    }

    @GetMapping("")
    public String processFindOwnersForm(@ModelAttribute("owner") Owner owner, BindingResult bindingResult, Model model) {
        // Allows parameterless GET request for /owners to return all records of Owners
        if (owner.getLastName() == null) {
            // Empty string signifies the largest possible search
            owner.setLastName("");
        }

        List<Owner> owners = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (owners.isEmpty()) {
            // No Owner(s) found
            bindingResult.rejectValue("lastName", "notFound", "not found");

            return "owners/findOwners";
        } else if (owners.size() == 1) {
            // Found One Owner
            owner = owners.get(0);

            return "redirect:/owners/" + owner.getId();
        } else {
            // Found multiple Owners
            model.addAttribute("listOwners", owners);

            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("/owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }
}