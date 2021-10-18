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
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Thomas");
        owner1.setLastName("Long");

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Kurt");
        owner2.setLastName("Proc");

        ownerService.save(owner1);
        ownerService.save(owner2);

        System.out.println("Loaded owners");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Pete");
        vet1.setLastName("Rolling");

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Eric");
        vet2.setLastName("Jones");

        vetService.save(vet2);

        System.out.println("Loaded vets");
    }
}