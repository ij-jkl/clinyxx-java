package org.clinyx.clinyxbackend.Services;

import org.clinyx.clinyxbackend.Dtos.DoctorDtos.DoctorCreateDto;
import org.clinyx.clinyxbackend.Dtos.DoctorDtos.DoctorDto;
import org.clinyx.clinyxbackend.Entities.DoctorEntity;
import org.clinyx.clinyxbackend.Entities.HealthInsuranceEntity;
import org.clinyx.clinyxbackend.Entities.SpecialtyEntity;
import org.clinyx.clinyxbackend.Exception.ResourceNotFoundException;
import org.clinyx.clinyxbackend.Interfaces.Repository.IDoctorRepository;
import org.clinyx.clinyxbackend.Interfaces.Services.IDoctorService;
import org.clinyx.clinyxbackend.Interfaces.Repository.IHealthInsuranceRepository;
import org.clinyx.clinyxbackend.Interfaces.Repository.ISpecialtyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements IDoctorService {

    private final IDoctorRepository _doctorRepository;
    private final ISpecialtyRepository _specialtyRepository;
    private final IHealthInsuranceRepository _healthInsuranceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DoctorServiceImpl(IDoctorRepository _doctorRepository, ISpecialtyRepository _specialtyRepository, IHealthInsuranceRepository _healthInsuranceRepository, ModelMapper modelMapper) {
        this._doctorRepository = _doctorRepository;
        this._specialtyRepository = _specialtyRepository;
        this._healthInsuranceRepository = _healthInsuranceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        List<DoctorEntity> doctors = _doctorRepository.findAll();

        return doctors.stream()
                .map(doctorEntity -> {
                    DoctorDto doctorDto = modelMapper.map(doctorEntity, DoctorDto.class);
                    return doctorDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DoctorEntity> getDoctorById(Long id) {
        return _doctorRepository.findById(id);
    }

    @Override
    @Transactional
    public DoctorEntity createDoctor(DoctorCreateDto doctorCreateDTO) {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName(doctorCreateDTO.getFirstName());
        doctor.setLastName(doctorCreateDTO.getLastName());
        doctor.setEmail(doctorCreateDTO.getEmail());
        doctor.setPhone(doctorCreateDTO.getPhone());
        doctor.setConsultationPrice(BigDecimal.valueOf(doctorCreateDTO.getConsultationPrice()));
        doctor.setDescription(doctorCreateDTO.getDescription());

        List<Integer> specialtyIds = doctorCreateDTO.getSpecialtyIds();
        if (specialtyIds != null && !specialtyIds.isEmpty()) {
            List<SpecialtyEntity> specialtiesList = _specialtyRepository.findAllById(specialtyIds);
            if (specialtiesList.size() != specialtyIds.size()) {
                throw new ResourceNotFoundException("One or more specialty IDs not found.");
            }
            Set<SpecialtyEntity> specialties = new HashSet<>(specialtiesList);
            doctor.setSpecialties(specialties);
        }

        List<Integer> healthInsuranceIds = doctorCreateDTO.getHealthInsuranceIds();
        if (healthInsuranceIds != null && !healthInsuranceIds.isEmpty()) {
            List<HealthInsuranceEntity> healthInsurancesList = _healthInsuranceRepository.findAllById(healthInsuranceIds);
            if (healthInsurancesList.size() != healthInsuranceIds.size()) {
                throw new ResourceNotFoundException("One or more health insurance IDs not found.");
            }
            Set<HealthInsuranceEntity> healthInsurances = new HashSet<>(healthInsurancesList);
            doctor.setHealthInsurances(healthInsurances);
        }

        return _doctorRepository.save(doctor);
    }

    @Override
    public DoctorEntity updateDoctor(Long id, DoctorEntity doctor) {
        Optional<DoctorEntity> existingDoctor = _doctorRepository.findById(id);

        if (existingDoctor.isPresent()) {
            DoctorEntity doctorToUpdate = existingDoctor.get();
            doctorToUpdate.setFirstName(doctor.getFirstName());
            doctorToUpdate.setLastName(doctor.getLastName());
            doctorToUpdate.setEmail(doctor.getEmail());
            doctorToUpdate.setPhone(doctor.getPhone());
            doctorToUpdate.setConsultationPrice(doctor.getConsultationPrice());
            doctorToUpdate.setDescription(doctor.getDescription());
            doctorToUpdate.setSpecialties(doctor.getSpecialties());
            doctorToUpdate.setHealthInsurances(doctor.getHealthInsurances());

            return _doctorRepository.save(doctorToUpdate);
        } else {
            return null;
        }
    }
}