package com.springframework.springpetclinic.bootstrap;

import com.springframework.springpetclinic.model.Owner;
import com.springframework.springpetclinic.model.Vet;
import com.springframework.springpetclinic.service.OwnerService;
import com.springframework.springpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// One way to initialize data: When Spring Context is fully
// loaded, then Springs calls the run(...)
@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("");
        owner.setLastName("");

        System.out.println("Loaded owners");

        Vet vet = new Vet();
        vet.setId(1L);
        vet.setFirstName("");
        vet.setLastName("");

        System.out.println("Loaded vets");
    }
}