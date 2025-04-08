package org.clinyx.clinyxbackend.Interfaces.Services;

import org.clinyx.clinyxbackend.Entities.SpecialtyEntity;

import java.util.List;
import java.util.Optional;

public interface ISpecialtyService {
    List<SpecialtyEntity> getAllSpecialties();
    Optional<SpecialtyEntity> getSpecialtyById(Integer id);
    SpecialtyEntity createSpecialty(SpecialtyEntity specialty);
    SpecialtyEntity updateSpecialty(Integer id, SpecialtyEntity specialty);
    void deleteSpecialty(Integer id);
}