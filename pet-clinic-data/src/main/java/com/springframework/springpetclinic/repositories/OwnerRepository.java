package com.springframework.springpetclinic.repositories;

import com.springframework.springpetclinic.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    // Derived Query Method: To find a Owner by Last Name Property
    Owner findByLastName(String lastName);
}