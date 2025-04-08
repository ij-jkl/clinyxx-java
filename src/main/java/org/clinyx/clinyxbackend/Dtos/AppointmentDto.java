package org.clinyx.clinyxbackend.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentDto {

    private Long idAppointment;
    private Long userId;
    private Long doctorId;
    private LocalDateTime appointmentDate;
    private String consultationReason;
    private String status;
}