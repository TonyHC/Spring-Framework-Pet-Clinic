package com.springframework.springpetclinic.repositories;

import com.springframework.springpetclinic.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetRepository extends JpaRepository<Vet, Long> {

}