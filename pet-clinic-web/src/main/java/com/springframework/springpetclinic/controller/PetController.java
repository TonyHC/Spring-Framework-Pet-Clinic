package com.springframework.springpetclinic.controller;

import com.springframework.springpetclinic.model.Owner;
import com.springframework.springpetclinic.model.Pet;
import com.springframework.springpetclinic.model.PetType;
import com.springframework.springpetclinic.services.OwnerService;
import com.springframework.springpetclinic.services.PetService;
import com.springframework.springpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetService petService, PetTypeService petTypeService, OwnerService ownerService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    // For each Controller Method, populate the Model with the Attribute 'owner' containing
    // the desired Owner. The Owner is founded by retrieving the Owner's id from the Controller's Mapping
    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String petCreationForm(@ModelAttribute("owner") Owner owner, Model model) {
        Pet pet = new Pet();
        owner.addPet(pet);

        model.addAttribute("pet", pet);

        return "/pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String processPetCreationForm(@Valid Pet pet, BindingResult bindingResult,
                                         @ModelAttribute("owner") Owner owner, Model model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            bindingResult.rejectValue("name", "duplicate", "already exists");
        }

        owner.addPet(pet);

        if (bindingResult.hasErrors()) {
            model.addAttribute("pet", pet);
            return "/pets/createOrUpdatePetForm";
        } else {
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String petUpdateForm(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId);

        model.addAttribute("pet", pet);

        return "/pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/{petId}/edit")
    public String processPetUpdateForm(@Valid Pet pet, BindingResult bindingResult,
                                       @ModelAttribute("owner") Owner owner, Model model) {
        if (bindingResult.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);

            return "/pets/createOrUpdatePetForm";
        } else {
            owner.addPet(pet);
            petService.save(pet);

            return "redirect:/owners/" + owner.getId();
        }
    }
}