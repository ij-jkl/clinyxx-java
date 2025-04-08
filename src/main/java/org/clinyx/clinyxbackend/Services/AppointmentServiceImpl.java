package org.clinyx.clinyxbackend.Services;

import org.clinyx.clinyxbackend.Dtos.AppointmentDtos.AppointmentCreateDto;
import org.clinyx.clinyxbackend.Dtos.AppointmentDtos.AppointmentDto;
import org.clinyx.clinyxbackend.Dtos.AppointmentDtos.AppointmentUpdateDto;
import org.clinyx.clinyxbackend.Entities.AppointmentEntity;
import org.clinyx.clinyxbackend.Entities.DoctorEntity;
import org.clinyx.clinyxbackend.Entities.UserEntity;
import org.clinyx.clinyxbackend.Exception.ResourceNotFoundException;
import org.clinyx.clinyxbackend.Interfaces.Repository.IAppointmentRepository;
import org.clinyx.clinyxbackend.Interfaces.Repository.IDoctorRepository;
import org.clinyx.clinyxbackend.Interfaces.Repository.IUserRepository;
import org.clinyx.clinyxbackend.Interfaces.Services.IAppointmentService;
import org.clinyx.clinyxbackend.Models.AppointmentStatusEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    private final IAppointmentRepository _appointmentRepository;
    private final IDoctorRepository _doctorRepository;
    private final IUserRepository _userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AppointmentServiceImpl(IAppointmentRepository appointmentRepository, IDoctorRepository doctorRepository, IUserRepository userRepository, ModelMapper modelMapper) { // Correct Autowiring
        this._appointmentRepository = appointmentRepository;
        this._doctorRepository = doctorRepository;
        this._userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {
        List<AppointmentEntity> appointments = _appointmentRepository.findAll();
        return appointments.stream()
                .map(appointmentEntity -> modelMapper.map(appointmentEntity, AppointmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AppointmentEntity> getAppointmentById(Long id) {
        return Optional.ofNullable(_appointmentRepository.findByIdWithUserAndDoctor(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id)));
    }

    @Override
    public AppointmentEntity createAppointment(AppointmentCreateDto appointmentCreateDto) {
        AppointmentEntity appointmentEntity = modelMapper.map(appointmentCreateDto, AppointmentEntity.class);

        UserEntity user = _userRepository.findById(appointmentCreateDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + appointmentCreateDto.getUserId()));

        DoctorEntity doctor = _doctorRepository.findById(appointmentCreateDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + appointmentCreateDto.getDoctorId()));

        appointmentEntity.setUser(user);
        appointmentEntity.setDoctor(doctor);
        appointmentEntity.setStatus(AppointmentStatusEnum.PENDING);

        return _appointmentRepository.save(appointmentEntity);
    }

    @Override
    public AppointmentEntity updateAppointment(Long id, AppointmentUpdateDto appointmentUpdateDto) {
        AppointmentEntity existingAppointment = _appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));


        UserEntity user = _userRepository.findById(appointmentUpdateDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + appointmentUpdateDto.getUserId()));

        DoctorEntity doctor = _doctorRepository.findById(appointmentUpdateDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + appointmentUpdateDto.getDoctorId()));

        existingAppointment.setDoctor(doctor);
        existingAppointment.setUser(user);
        existingAppointment.setAppointmentDate(appointmentUpdateDto.getAppointmentDate());

        existingAppointment.setConsultationReason(appointmentUpdateDto.getConsultationReason());
        existingAppointment.setStatus(AppointmentStatusEnum.valueOf(appointmentUpdateDto.getStatus()));

        return _appointmentRepository.save(existingAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        _appointmentRepository.deleteById(id);
    }
}