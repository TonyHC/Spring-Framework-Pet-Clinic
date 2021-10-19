package com.springframework.springpetclinic.bootstrap;

import com.springframework.springpetclinic.model.Owner;
import com.springframework.springpetclinic.model.PetType;
import com.springframework.springpetclinic.model.Vet;
import com.springframework.springpetclinic.service.OwnerService;
import com.springframework.springpetclinic.service.PetTypeService;
import com.springframework.springpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// One way to initialize data: When Spring Context is fully
// loaded, then Springs calls the run(...)
@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Thomas");
        owner1.setLastName("Long");

        Owner owner2 = new Owner();
        owner2.setFirstName("Kurt");
        owner2.setLastName("Proc");

        ownerService.save(owner1);
        ownerService.save(owner2);

        System.out.println("Loaded owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Pete");
        vet1.setLastName("Rolling");

        Vet vet2 = new Vet();
        vet2.setFirstName("Eric");
        vet2.setLastName("Jones");

        vetService.save(vet1);
        vetService.save(vet2);

        System.out.println("Loaded vets");
    }
}