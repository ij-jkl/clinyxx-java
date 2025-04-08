package org.clinyx.clinyxbackend.Interfaces.Repository;

import org.clinyx.clinyxbackend.Entities.HealthInsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHealthInsuranceRepository extends JpaRepository<HealthInsuranceEntity, Integer> {
}
