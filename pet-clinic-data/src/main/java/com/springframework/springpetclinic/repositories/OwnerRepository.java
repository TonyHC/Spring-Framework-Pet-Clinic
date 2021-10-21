package com.springframework.springpetclinic.repositories;

import com.springframework.springpetclinic.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}