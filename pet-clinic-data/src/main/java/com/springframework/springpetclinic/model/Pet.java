package com.springframework.springpetclinic.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {
    @Column(name = "name")
    private String name;

    // Many To One Uni-Directional Relationship: Many Pets have One PetType
    // Specify the Foreign Key used to join the Entity Association (Pets and PetType)
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
    private LocalDate birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}