package com.springframework.springpetclinic.services.map;

import com.springframework.springpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {
    PetMapService petMapService;

    final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().id(petId).build());
    }

    @Test
    void findAllTest() {
        Set<Pet> pets = petMapService.findAll();
        assertEquals(1, pets.size());
    }

    @Test
    void findByIdExistingIdTest() {
        Pet pet = petMapService.findById(petId);
        assertEquals(petId, pet.getId());
        assertNotNull(pet);
    }

    @Test
    void findByIdNotExistingIdTest() {
        Pet pet = petMapService.findById(2L);
        assertNull(pet);
    }

    @Test
    void findByIdNullIdTest() {
        Pet pet = petMapService.findById(null);
        assertNull(pet);
    }

    @Test
    void deleteByIdCorrectIdTest() {
        petMapService.deleteById(petId);
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteByIdIncorrectIdTest() {
        petMapService.deleteById(2L);
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteExistingPet() {
        Pet pet = petMapService.findById(petId);
        petMapService.delete(pet);
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteNonExistingPet() {
        Pet pet = petMapService.findById(2L);
        petMapService.delete(pet);
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void saveNewPet() {
        Long petId = 2L;
        Pet pet = Pet.builder().id(petId).build();

        petMapService.save(pet);

        assertEquals(petId, pet.getId());
    }

    @Test
    void saveDuplicatePet() {
        Long existingPetId = 1L;
        Pet pet = Pet.builder().id(existingPetId).build();

        petMapService.save(pet);

        assertEquals(existingPetId, pet.getId());
        assertEquals(1, petMapService.findAll().size());
    }
}