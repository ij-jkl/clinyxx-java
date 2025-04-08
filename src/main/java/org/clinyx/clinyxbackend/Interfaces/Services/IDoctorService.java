package org.clinyx.clinyxbackend.Interfaces.Services;

import org.clinyx.clinyxbackend.Dtos.DoctorDtos.DoctorCreateDto;
import org.clinyx.clinyxbackend.Dtos.DoctorDtos.DoctorDto;
import org.clinyx.clinyxbackend.Entities.DoctorEntity;

import java.util.List;
import java.util.Optional;

public interface IDoctorService {
    List<DoctorDto> getAllDoctors();
    Optional<DoctorEntity> getDoctorById(Long id);
    DoctorEntity createDoctor(DoctorCreateDto doctor);
    DoctorEntity updateDoctor(Long id, DoctorEntity doctor);
}