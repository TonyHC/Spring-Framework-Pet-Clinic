package com.springframework.springpetclinic.formatter;

import com.springframework.springpetclinic.model.PetType;
import com.springframework.springpetclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {
    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        // Get all PetTypes from PetService
        Collection<PetType> findPetTypes = petTypeService.findAll();

        // Iterate through all PetTypes
        for (PetType petType : findPetTypes) {
            // If PetType's Name equals Text
            if (petType.getName().equals(text)) {
                // Return specific PetType
                return petType;
            }
        }

        // Otherwise, throw ParseException
        throw new ParseException("PetType not found: " + text, 0);
    }

    @Override
    public String print(PetType pet, Locale locale) {
        return pet.getName();
    }
}