package com.springframework.springpetclinic.services.springdatajpa;

import com.springframework.springpetclinic.model.Owner;
import com.springframework.springpetclinic.repositories.OwnerRepository;
import com.springframework.springpetclinic.repositories.PetRepository;
import com.springframework.springpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSpringDataJpaServiceTest {
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSpringDataJpaService ownerSpringDataJpaService;

    @Test
    void findAll() {
        // Given
        List<Owner> returnOwners = new ArrayList<>();
        returnOwners.add(Owner.builder().id(1L).build());
        returnOwners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnOwners);

        // When
        Set<Owner> owners = ownerSpringDataJpaService.findAll();

        // Then
        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findByIdFound() {
        // Given
        Owner returnOwner = Owner.builder().id(1L).build();

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        // When
        Owner owner = ownerSpringDataJpaService.findById(6L);

        // Then
        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        // Given
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        Owner owner = ownerSpringDataJpaService.findById(6L);

        // Then
        assertNull(owner);
    }

    @Test
    void save() {
        // Given
        Owner returnOwner = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        // When
        Owner savedOwner = ownerSpringDataJpaService.save(any());

        // Then
        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        // Given
        Owner returnOwner = Owner.builder().id(1L).build();

        // When
        ownerSpringDataJpaService.delete(returnOwner);

        // Then
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        // Given
        Long ownerId = 1L;

        // When
        ownerSpringDataJpaService.deleteById(ownerId);

        // Then
        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        // Given
        String ownerLastName = "Owns";
        Owner returnOwner = Owner.builder().id(1L).lastName(ownerLastName).build();

        when(ownerRepository.findByLastName(anyString())).thenReturn(returnOwner);

        // When
        Owner owner = ownerSpringDataJpaService.findByLastName("");

        // Then
        assertEquals(ownerLastName, owner.getLastName());
        verify(ownerRepository, times(1)).findByLastName(anyString());
    }
}