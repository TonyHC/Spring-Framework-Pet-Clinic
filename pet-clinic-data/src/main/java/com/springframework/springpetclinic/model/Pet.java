package com.springframework.springpetclinic.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {
    @Column(name = "name")
    private String name;

    // Many To One Uni-Directional Relationship: Many Pets have One PetType
    // Specify the Foreign Key 'type_id' used to join the Entity Association (Pets and PetType)
    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    // Many To One Bi-Directional Relationship: Many Pets have One Owner
    // @JoinColumn helps us specify the column we'll use for joining on Entity association or element collection
    // The Owning Side is usually defined on the 'many' side of the relationship. Usually side which owns Foreign Key
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    // One To Many Bi-Directional Relationship: One Pet can have Many Visits
    // 'mappedBy' establishes that Pet owns the relationship (owning side) and
    // makes this association Bi-Directional
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits;

    @Builder
    public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;
        this.visits = visits;
    }

    // Helper Method: To add a Visit object and
    // establish the newly added Visit object has a relationship with this Pet
    public void addVisit(Visit visit) {
        if (visits == null) {
            visits = new HashSet<>();
        }

        visits.add(visit);
        visit.setPet(this);
    }
}