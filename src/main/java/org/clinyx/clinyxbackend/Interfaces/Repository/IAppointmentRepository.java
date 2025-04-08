package org.clinyx.clinyxbackend.Interfaces.Repository;

import org.clinyx.clinyxbackend.Entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    @Query("SELECT a FROM AppointmentEntity a JOIN FETCH a.user JOIN FETCH a.doctor WHERE a.idAppointment = :id")
    Optional<AppointmentEntity> findByIdWithUserAndDoctor(@Param("id") Long id);
}