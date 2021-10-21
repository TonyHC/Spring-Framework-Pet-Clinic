package com.springframework.springpetclinic.services;

import java.util.Set;

// Service Interface to mimic the Spring Repository
public interface CrudService<T, ID> {
    Set<T> findAll();
    T findById(ID id);
    T save(T object);
    void delete(T object);
    void deleteById(ID id);
}