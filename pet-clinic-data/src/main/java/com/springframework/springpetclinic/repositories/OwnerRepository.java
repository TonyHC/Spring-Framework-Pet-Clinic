package com.springframework.springpetclinic.repositories;

import com.springframework.springpetclinic.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    // Derived Query Method: To find an Owner by Last Name Property
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}