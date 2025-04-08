package org.clinyx.clinyxbackend.Interfaces.Repository;

import org.clinyx.clinyxbackend.Entities.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IDoctorRepository extends JpaRepository<DoctorEntity, Long> {
    Optional<DoctorEntity> findByEmail(String email);
}
