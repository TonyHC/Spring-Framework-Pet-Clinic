package com.springframework.springpetclinic.bootstrap;

import com.springframework.springpetclinic.model.*;
import com.springframework.springpetclinic.service.OwnerService;
import com.springframework.springpetclinic.service.PetTypeService;
import com.springframework.springpetclinic.service.SpecialityService;
import com.springframework.springpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// One way to initialize data: When Spring Context is fully
// loaded, then Springs calls the run(...)
@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Thomas");
        owner1.setLastName("Long");
        owner1.setAddress("123 Test Street");
        owner1.setCity("Lars");
        owner1.setTelephone("4591234567");

        Pet thomasPet = new Pet();
        thomasPet.setName("Rover");
        thomasPet.setPetType(savedDogPetType);
        thomasPet.setBirthDate(LocalDate.now());
        thomasPet.setOwner(owner1);
        owner1.getPets().add(thomasPet);

        Owner owner2 = new Owner();
        owner2.setFirstName("Kurt");
        owner2.setLastName("Proc");
        owner2.setAddress("98 Avenue");
        owner2.setCity("Bonot");
        owner2.setTelephone("4595934567");

        Pet kurtsPet = new Pet();
        kurtsPet.setName("Lapis");
        kurtsPet.setPetType(savedCatPetType);
        kurtsPet.setBirthDate(LocalDate.now());
        kurtsPet.setOwner(owner2);
        owner2.getPets().add(kurtsPet);

        ownerService.save(owner1);
        ownerService.save(owner2);

        System.out.println("Loaded owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Pete");
        vet1.setLastName("Rolling");
        vet1.getSpecialities().add(savedDentistry);

        Vet vet2 = new Vet();
        vet2.setFirstName("Eric");
        vet2.setLastName("Jones");
        vet2.getSpecialities().add(radiology);

        vetService.save(vet1);
        vetService.save(vet2);

        System.out.println("Loaded vets");
    }
}