package com.springframework.springpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private Set<Speciality> specialities = new HashSet<>();
}