package org.clinyx.clinyxbackend.Services;

import org.clinyx.clinyxbackend.Entities.SpecialtyEntity;
import org.clinyx.clinyxbackend.Interfaces.Repository.ISpecialtyRepository;
import org.clinyx.clinyxbackend.Interfaces.Services.ISpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyServiceImpl implements ISpecialtyService {

    private final ISpecialtyRepository specialtyRepository;

    @Autowired
    public SpecialtyServiceImpl(ISpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public List<SpecialtyEntity> getAllSpecialties() {
        return specialtyRepository.findAll();
    }

    @Override
    public Optional<SpecialtyEntity> getSpecialtyById(Integer id) {
        return specialtyRepository.findById(id);
    }

    @Override
    public SpecialtyEntity createSpecialty(SpecialtyEntity specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public SpecialtyEntity updateSpecialty(Integer id, SpecialtyEntity specialty) {
        Optional<SpecialtyEntity> existingSpecialty = specialtyRepository.findById(id);

        if (existingSpecialty.isPresent()) {
            SpecialtyEntity specialtyToUpdate = existingSpecialty.get();
            specialtyToUpdate.setSpecialtyName(specialty.getSpecialtyName());
            return specialtyRepository.save(specialtyToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void deleteSpecialty(Integer id) { // Changed to Integer
        specialtyRepository.deleteById(id);
    }
}