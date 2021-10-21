package com.springframework.springpetclinic.repositories;

import com.springframework.springpetclinic.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {

}