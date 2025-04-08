package org.clinyx.clinyxbackend.Controllers;

import org.clinyx.clinyxbackend.Dtos.AppointmentCreateDto;
import org.clinyx.clinyxbackend.Dtos.AppointmentDto;
import org.clinyx.clinyxbackend.Dtos.AppointmentUpdateDto;
import org.clinyx.clinyxbackend.Entities.AppointmentEntity;
import org.clinyx.clinyxbackend.Interfaces.Services.IAppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final IAppointmentService _appointmentService;
    private final ModelMapper modelMapper;

    @Autowired
    public AppointmentController(IAppointmentService appointmentService, ModelMapper modelMapper) {
        this._appointmentService = appointmentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/get_all_appointments")
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        return ResponseEntity.ok(_appointmentService.getAllAppointments());
    }

    @GetMapping("/get_appointment_by_{id}")
    public ResponseEntity<AppointmentEntity> getAppointmentById(@PathVariable Long id) {
        Optional<AppointmentEntity> appointment = _appointmentService.getAppointmentById(id);
        return appointment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create_appointment")
    public ResponseEntity<AppointmentEntity> createAppointment(@RequestBody AppointmentCreateDto appointmentCreateDto) {
        AppointmentEntity createdAppointment = _appointmentService.createAppointment(appointmentCreateDto);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    @PutMapping("/update_appointment/{id}")
    public ResponseEntity<AppointmentEntity> updateAppointment(@PathVariable Long id, @RequestBody AppointmentUpdateDto appointmentUpdateDto) {
        try {
            AppointmentEntity updatedAppointment = _appointmentService.updateAppointment(id, appointmentUpdateDto);
            return ResponseEntity.ok(updatedAppointment);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete_appointment/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        _appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}