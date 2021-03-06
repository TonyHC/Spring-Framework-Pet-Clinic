package com.springframework.springpetclinic.services.map;

import com.springframework.springpetclinic.model.Speciality;
import com.springframework.springpetclinic.model.Vet;
import com.springframework.springpetclinic.services.SpecialityService;
import com.springframework.springpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Profile({"default", "map"})
@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {
    private final SpecialityService specialityService;

    public VetMapService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        // If Vet has at least one Speciality
        if (object.getSpecialities().size() > 0) {
            // Go through each Speciality
            object.getSpecialities().forEach(speciality -> {
                // If Speciality doesn't have an ID
                if (speciality.getId() == null) {
                    // Save Speciality to Map Service (auto generated ID for us)
                    Speciality savedSpeciality = specialityService.save(speciality);
                    speciality.setId(savedSpeciality.getId());
                }
            });
        }

        // Save Vet to Map Service
        return super.save(object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}