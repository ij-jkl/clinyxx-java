package org.clinyx.clinyxbackend.Controllers;

import org.clinyx.clinyxbackend.Dtos.SpecialtyRequestDto;
import org.clinyx.clinyxbackend.Entities.SpecialtyEntity;
import org.clinyx.clinyxbackend.Interfaces.Services.ISpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/specialties")
public class SpecialtyController {

    private final ISpecialtyService specialtyService;

    @Autowired
    public SpecialtyController(ISpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @GetMapping
    public ResponseEntity<List<SpecialtyEntity>> getAllSpecialties() {
        List<SpecialtyEntity> specialties = specialtyService.getAllSpecialties();
        return new ResponseEntity<>(specialties, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyEntity> getSpecialtyById(@PathVariable Integer id) {
        Optional<SpecialtyEntity> specialty = specialtyService.getSpecialtyById(id);
        return specialty.map(specialtyEntity -> new ResponseEntity<>(specialtyEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<SpecialtyEntity> createSpecialty(@RequestBody SpecialtyRequestDto specialtyRequest) {
        SpecialtyEntity specialty = new SpecialtyEntity();
        specialty.setSpecialtyName(specialtyRequest.getSpecialtyName());
        SpecialtyEntity createdSpecialty = specialtyService.createSpecialty(specialty);
        return new ResponseEntity<>(createdSpecialty, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyEntity> updateSpecialty(@PathVariable Integer id, @RequestBody SpecialtyRequestDto specialtyRequest) {
        SpecialtyEntity specialty = new SpecialtyEntity();
        specialty.setSpecialtyName(specialtyRequest.getSpecialtyName());

        SpecialtyEntity updatedSpecialty = specialtyService.updateSpecialty(id, specialty);
        if (updatedSpecialty != null) {
            return new ResponseEntity<>(updatedSpecialty, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialty(@PathVariable Integer id) {
        specialtyService.deleteSpecialty(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}