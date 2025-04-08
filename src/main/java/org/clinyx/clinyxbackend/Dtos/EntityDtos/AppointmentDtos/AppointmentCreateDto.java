package org.clinyx.clinyxbackend.Dtos.EntityDtos.AppointmentDtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentCreateDto {

    private Long userId;
    private Long doctorId;
    private LocalDateTime appointmentDate;
    private String consultationReason;

}