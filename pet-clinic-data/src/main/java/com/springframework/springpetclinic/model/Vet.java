package com.springframework.springpetclinic.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vets")
public class Vet extends Person {
    // Many To Many Uni-Directional Relationship: Many Vets have Many Specialities
    // Use Eager Fetching to load all Vets and its Specialities
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialities",
        joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id")
    )
    private Set<Speciality> specialities;

    public Vet() {
        specialities = new HashSet<>();
    }

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}