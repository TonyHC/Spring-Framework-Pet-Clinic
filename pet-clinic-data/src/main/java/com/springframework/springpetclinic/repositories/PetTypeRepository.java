package com.springframework.springpetclinic.repositories;

import com.springframework.springpetclinic.model.PetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {

}