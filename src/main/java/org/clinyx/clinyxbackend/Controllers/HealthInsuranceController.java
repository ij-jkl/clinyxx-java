package org.clinyx.clinyxbackend.Controllers;

import jakarta.validation.Valid;
import org.clinyx.clinyxbackend.Dtos.EntityDtos.HealthInsuranceDtos.HealthInsuranceDto;
import org.clinyx.clinyxbackend.Entities.HealthInsuranceEntity;
import org.clinyx.clinyxbackend.Interfaces.Services.IHealthInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/health-insurances")
public class HealthInsuranceController {

    private final IHealthInsuranceService healthInsuranceService;

    @Autowired
    public HealthInsuranceController(IHealthInsuranceService healthInsuranceService) {
        this.healthInsuranceService = healthInsuranceService;
    }

    @GetMapping
    public ResponseEntity<List<HealthInsuranceEntity>> getAllHealthInsurances() {
        List<HealthInsuranceEntity> healthInsurances = healthInsuranceService.getAllHealthInsurances();
        return new ResponseEntity<>(healthInsurances, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthInsuranceEntity> getHealthInsuranceById(@PathVariable Integer id) {
        Optional<HealthInsuranceEntity> healthInsurance = healthInsuranceService.getHealthInsuranceById(id);
        return healthInsurance.map(insurance -> new ResponseEntity<>(insurance, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<HealthInsuranceEntity> createHealthInsurance(@Valid @RequestBody HealthInsuranceDto healthInsuranceDto) {
        HealthInsuranceEntity healthInsurance = new HealthInsuranceEntity();
        healthInsurance.setInsuranceName(healthInsuranceDto.getInsuranceName());
        HealthInsuranceEntity createdInsurance = healthInsuranceService.createHealthInsurance(healthInsurance);
        return new ResponseEntity<>(createdInsurance, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthInsuranceEntity> updateHealthInsurance(@PathVariable Integer id, @Valid @RequestBody HealthInsuranceDto healthInsuranceDto) {
        HealthInsuranceEntity healthInsurance = new HealthInsuranceEntity();
        healthInsurance.setInsuranceName(healthInsuranceDto.getInsuranceName());

        HealthInsuranceEntity updatedInsurance = healthInsuranceService.updateHealthInsurance(id, healthInsurance);
        if (updatedInsurance != null) {
            return new ResponseEntity<>(updatedInsurance, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHealthInsurance(@PathVariable Integer id) {
        healthInsuranceService.deleteHealthInsurance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}