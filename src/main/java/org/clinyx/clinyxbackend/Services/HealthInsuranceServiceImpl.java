package org.clinyx.clinyxbackend.Services;

import org.clinyx.clinyxbackend.Entities.HealthInsuranceEntity;
import org.clinyx.clinyxbackend.Interfaces.Repository.IHealthInsuranceRepository;
import org.clinyx.clinyxbackend.Interfaces.Services.IHealthInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthInsuranceServiceImpl implements IHealthInsuranceService {

    private final IHealthInsuranceRepository healthInsuranceRepository;

    @Autowired
    public HealthInsuranceServiceImpl(IHealthInsuranceRepository healthInsuranceRepository) {
        this.healthInsuranceRepository = healthInsuranceRepository;
    }

    @Override
    public List<HealthInsuranceEntity> getAllHealthInsurances() {
        return healthInsuranceRepository.findAll();
    }

    @Override
    public Optional<HealthInsuranceEntity> getHealthInsuranceById(Integer id) {
        return healthInsuranceRepository.findById(id);
    }

    @Override
    public HealthInsuranceEntity createHealthInsurance(HealthInsuranceEntity healthInsurance) {
        return healthInsuranceRepository.save(healthInsurance);
    }

    @Override
    public HealthInsuranceEntity updateHealthInsurance(Integer id, HealthInsuranceEntity healthInsurance) {
        Optional<HealthInsuranceEntity> existingHealthInsurance = healthInsuranceRepository.findById(id);

        if (existingHealthInsurance.isPresent()) {
            HealthInsuranceEntity healthInsuranceToUpdate = existingHealthInsurance.get();
            healthInsuranceToUpdate.setInsuranceName(healthInsurance.getInsuranceName());
            return healthInsuranceRepository.save(healthInsuranceToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void deleteHealthInsurance(Integer id) {
        healthInsuranceRepository.deleteById(id);
    }
}