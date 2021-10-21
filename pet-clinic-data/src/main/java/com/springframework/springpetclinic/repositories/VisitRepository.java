package com.springframework.springpetclinic.repositories;

import com.springframework.springpetclinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {

}