package com.springframework.springpetclinic.repositories;

import com.springframework.springpetclinic.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {

}