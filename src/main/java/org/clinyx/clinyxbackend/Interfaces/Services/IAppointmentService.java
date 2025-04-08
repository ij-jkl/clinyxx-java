package org.clinyx.clinyxbackend.Interfaces.Services;

import org.clinyx.clinyxbackend.Dtos.EntityDtos.AppointmentDtos.AppointmentCreateDto;
import org.clinyx.clinyxbackend.Dtos.EntityDtos.AppointmentDtos.AppointmentDto;
import org.clinyx.clinyxbackend.Dtos.EntityDtos.AppointmentDtos.AppointmentUpdateDto;
import org.clinyx.clinyxbackend.Entities.AppointmentEntity;

import java.util.List;
import java.util.Optional;

public interface IAppointmentService {

    List<AppointmentDto> getAllAppointments();
    Optional<AppointmentEntity> getAppointmentById(Long id);
    AppointmentEntity createAppointment(AppointmentCreateDto appointmentCreateDto);
    AppointmentEntity updateAppointment(Long id, AppointmentUpdateDto appointmentUpdateDto);
    void deleteAppointment(Long id);
}