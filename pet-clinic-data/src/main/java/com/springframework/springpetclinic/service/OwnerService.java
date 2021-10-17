package com.springframework.springpetclinic.service;

import com.springframework.springpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}