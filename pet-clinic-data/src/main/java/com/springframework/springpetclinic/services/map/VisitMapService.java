package com.springframework.springpetclinic.services.map;

import com.springframework.springpetclinic.model.Visit;
import com.springframework.springpetclinic.services.VisitService;

import java.util.Set;

public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {
    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit object) {
        // Defensive Coding: If Pet or Owner doesn't exist or doesn't have an ID Property Set, thrown RuntimeException
        if (object.getPet() == null || object.getPet().getOwner() == null || object.getPet().getId() == null
            || object.getPet().getOwner().getId() == null) {
            throw new RuntimeException("Invalid Visit");
        }

        // Otherwise, return a valid Visit object
        return super.save(object);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}