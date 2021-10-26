package com.springframework.springpetclinic.controller;

import com.springframework.springpetclinic.model.Owner;
import com.springframework.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
        Owner owner = Owner.builder().build();

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

    @GetMapping("/new")
    public String ownerCreationForm(Model model) {
        Owner owner = Owner.builder().build();

        model.addAttribute("owner", owner);

        return "/owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processOwnerCreationForm(@Valid @ModelAttribute("owner") Owner owner,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/owners/createOrUpdateOwnerForm";
        } else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String ownerUpdateForm(@PathVariable Long ownerId, Model model) {
        Owner owner = ownerService.findById(ownerId);

        model.addAttribute("owner", owner);

        return "/owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/{ownerId}/edit")
    public String processOwnerUpdateForm(@Valid @ModelAttribute("owner") Owner owner,
                                         BindingResult bindingResult, @PathVariable Long ownerId) {
        if (bindingResult.hasErrors()) {
            return "/owners/createOrUpdateOwnerForm";
        } else {
            // We have to manually set the id property of Owner due to @InitBinder method,
            // which disallows the property (or field) 'id' from being binded
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}