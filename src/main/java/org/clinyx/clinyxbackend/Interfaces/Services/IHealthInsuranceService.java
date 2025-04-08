package org.clinyx.clinyxbackend.Interfaces.Services;

import org.clinyx.clinyxbackend.Entities.HealthInsuranceEntity;

import java.util.List;
import java.util.Optional;

public interface IHealthInsuranceService {
    List<HealthInsuranceEntity> getAllHealthInsurances();
    Optional<HealthInsuranceEntity> getHealthInsuranceById(Integer id);
    HealthInsuranceEntity createHealthInsurance(HealthInsuranceEntity healthInsurance);
    HealthInsuranceEntity updateHealthInsurance(Integer id, HealthInsuranceEntity healthInsurance);
    void deleteHealthInsurance(Integer id);
}