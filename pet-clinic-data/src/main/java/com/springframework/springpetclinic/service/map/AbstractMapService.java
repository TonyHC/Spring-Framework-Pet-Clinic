package com.springframework.springpetclinic.service.map;

import com.springframework.springpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T object) {
        // If object not null and id is null
        if (object != null && object.getId() == null) {
            // Get the current id
            object.setId(getNextId());

            map.put(object.getId(), object);
        } else {
            // Else throw a RuntimeException
            throw new RuntimeException("Object cannot be null");
        }

        return object;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    // Helper Method: To generate the next id for HashMap
    private Long getNextId() {
        Long nextId = null;

        try {
            // If Hashmap has at least 1 Key-Value Pair, then set the id of next Key-Value Pair as num of keys + 1
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException exception) {
            // If HashMap has no Key-Value Pairs, then set the id of first Key-Value Pair as 1
            nextId = 1L;
        }

        return nextId;
    }
}