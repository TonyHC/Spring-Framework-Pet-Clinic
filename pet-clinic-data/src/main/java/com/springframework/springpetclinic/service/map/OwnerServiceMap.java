package com.springframework.springpetclinic.service.map;

import com.springframework.springpetclinic.model.Owner;
import com.springframework.springpetclinic.model.Pet;
import com.springframework.springpetclinic.service.OwnerService;
import com.springframework.springpetclinic.service.PetService;
import com.springframework.springpetclinic.service.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerServiceMap(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner object) {
        // If Owner object not null
        if (object != null) {
            // If Owner's has Pet(s)
            if (object.getPets() != null) {
                // Go through each Pet
                object.getPets().forEach(pet -> {
                    // If Pet has a Pet Type
                    if (pet.getPetType() != null) {
                        // If Pet's PetType ID is null
                        if (pet.getPetType().getId() == null) {
                            // Save PetType to Map's Service and Set the Pet's PetType
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else { // Throw RuntimeException
                        throw new RuntimeException("Pet Type is required");
                    }

                    // If Pet's ID is null
                    if (pet.getId() == null) {
                        // Save Pet to Map's Service
                        Pet savedPet = petService.save(pet);
                        savedPet.setId(savedPet.getId());
                    }
                });
            }
        }

        // Save Owner object
        return super.save(object);
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}