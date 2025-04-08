package org.clinyx.clinyxbackend.Controllers;

import org.clinyx.clinyxbackend.Dtos.EntityDtos.DoctorDtos.DoctorCreateDto;
import org.clinyx.clinyxbackend.Dtos.EntityDtos.DoctorDtos.DoctorDto;
import org.clinyx.clinyxbackend.Entities.DoctorEntity;
import org.clinyx.clinyxbackend.Interfaces.Services.IDoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final IDoctorService _doctorService;
    private final ModelMapper modelMapper;

    @Autowired
    public DoctorController(IDoctorService _doctorService, ModelMapper modelMapper) {
        this._doctorService = _doctorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/get_all_doctors")
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        return ResponseEntity.ok(_doctorService.getAllDoctors());
    }

    @GetMapping("/get_doctors_by_{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long id) {
        Optional<DoctorEntity> doctor = _doctorService.getDoctorById(id);
        if (doctor.isPresent()) {
            DoctorDto doctorDto = modelMapper.map(doctor.get(), DoctorDto.class);
            return ResponseEntity.ok(doctorDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("create_doctor")
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorCreateDto doctorCreateDto) {
        DoctorEntity createdDoctorEntity = _doctorService.createDoctor(doctorCreateDto);
        DoctorDto createdDoctorDto = modelMapper.map(createdDoctorEntity, DoctorDto.class);
        return new ResponseEntity<>(createdDoctorDto, HttpStatus.CREATED);
    }

    @PutMapping("update_doctor")
    public ResponseEntity<DoctorDto> updateDoctor(@RequestBody DoctorDto doctorDto) {
        Long id = doctorDto.getIdDoctor();

        DoctorEntity doctorEntity = modelMapper.map(doctorDto, DoctorEntity.class);

        DoctorEntity updatedDoctorEntity = _doctorService.updateDoctor(id, doctorEntity);

        if (updatedDoctorEntity != null) {
            DoctorDto updatedDoctorDto = modelMapper.map(updatedDoctorEntity, DoctorDto.class);
            return ResponseEntity.ok(updatedDoctorDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}